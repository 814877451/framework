package com.mgl.framework.utils;


import com.mgl.framework.exception.ExceptionEnums;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;

/**
 *@Description: 雪花算法工具类
 *  数据机构: 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 *  第一位为未使用，接下来的41位为毫秒级时间(41位的长度可以使用69年)，然后是5位datacenterId和5位workerId(10位的长度最多支持部署1024个节点） ，
 *  最后12位是毫秒内的计数（12位的计数顺序号支持每个节点每毫秒产生4096个ID序号）一共加起来刚好64位，为一个Long型。(转换成字符串后长度最多19)。
 *  snowflake生成的ID整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞（由datacenter和workerId作区分）
 *
 *@Author: magla
 *@Date: 2019/6/3_9:24
 *@since: v1.0.0
 */
public class SnowflakeIdUtils {

    /** 开始时间截 (2015-01-01) */
    private static final long twepoch = 1420041600000L;
    /** 机器id所占的位数 */
    private static final long workerIdBits = 5L;
    /** 数据标识id所占的位数 */
    private static final long datacenterIdBits = 5L;
    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /** 支持的最大数据标识id，结果是31 */
    private static final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    /** 序列在id中占的位数 */
    private static final long sequenceBits = 12L;
    /** 机器ID向左移12位 */
    private static final long workerIdShift = sequenceBits;
    /** 数据标识id向左移17位(12+5) */
    private static final long datacenterIdShift = sequenceBits + workerIdBits;
    /** 时间截向左移22位(5+5+12) */
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);
    /** 工作机器ID(0~31) */
    private static long workerId;
    /** 数据中心ID(0~31) */
    private static long datacenterId;
    /** 毫秒内序列(0~4095) */
    private static long sequence = 0L;
    /** 上次生成ID的时间截 */
    private static long lastTimestamp = -1L;

    /*static {
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException var3) {
            throw ExceptionEnums.SNOWFLAKE_EXCEPTION.expMsg("雪花算法:::获取本地IP异常");
        }
        byte[] ipAddressByteArray = address.getAddress();
        workerId = (long)(((ipAddressByteArray[ipAddressByteArray.length - 2] & 3) << 8) + (ipAddressByteArray[ipAddressByteArray.length - 1] & 255));
        if (workerId > maxWorkerId) {
            throw ExceptionEnums.SNOWFLAKE_EXCEPTION.expMsg("雪花算法:::机器码大于最大值");
        }else if (datacenterId > maxDatacenterId) {
            throw ExceptionEnums.SNOWFLAKE_EXCEPTION.expMsg("雪花算法:::数据标识大于最大值");
        }
    }*/

    public static Long generate() {
        return nexeId();
    }


    public static synchronized long nexeId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            long delay = lastTimestamp -timestamp;
            if (delay < 5L) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timestamp = timeGen();
            }

            if (timestamp >= lastTimestamp) {
                return  generate();
            }

            throw ExceptionEnums.SNOWFLAKE_EXCEPTION.expMsg("当前时间截小于上次时间截:::".concat("当前时间截为")
                    .concat(String.valueOf(timestamp).concat(",")
                            .concat("上次时间截为").concat(String.valueOf(lastTimestamp))));
        }

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        }else {
            sequence = (long)(new SecureRandom()).nextInt(10);
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }


    public static void main(String[] args) throws UnknownHostException {
        int i = 0;
        while (i < 50) {
            System.out.println(String.valueOf(generate()));
            i++;
        }

        InetAddress address = InetAddress.getLocalHost();
        byte[] ipAddressByteArray = address.getAddress();
        workerId = (long)(((ipAddressByteArray[ipAddressByteArray.length - 2] & 3) << 8) + (ipAddressByteArray[ipAddressByteArray.length - 1] & 255));
        System.out.println(address.toString());
        for (byte data : ipAddressByteArray) {
            System.out.println(data);
        }
        System.out.println(workerId);

    }
}
