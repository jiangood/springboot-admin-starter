package io.admin.common.utils;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.unit.DataSizeUtil;

import java.util.Date;

public class FriendlyUtils {

    /**
     * 将小数转换为友好的百分比显示格式
     *
     * @param value 小数值（0-1之间）
     * @param decimalPlaces 保留的小数位数
     * @return 百分比字符串，例如"85.5%"
     */
    public static String getPercentage(double value, int decimalPlaces) {
        return String.format("%.2" + decimalPlaces + "f%%", value * 100);
    }

    /**
     * 将布尔值转换为友好的中文显示
     *
     * @param value 布尔值
     * @return "是"或"否"
     */
    public static String getBoolean(boolean value) {
        return value ? "是" : "否";
    }


    /**
     * 将布尔值转换为图标显示
     *
     * @param value 布尔值
     * @return "✓"或"✗"
     */
    public static String getIconBoolean(boolean value) {
        return value ? "✓" : "✗";
    }


    /**
     * 获取格式化后的数据大小字符串
     *
     * @param size 数据大小，以字节为单位
     * @return 格式化后的数据大小字符串，例如"1.5MB"、"2.3GB"等
     */
    public static String getDataSize(long size) {
        return DataSizeUtil.format(size);
    }


    /**
     * 计算过去了多少时间
     */
    public static String getPastTime(Date date) {
        float between = DateUtil.between(date, new Date(), DateUnit.MINUTE, false);
        if (between < 1) {
            return "刚刚";
        }
        if (between < 60) {
            return between + "分钟前";
        }

        between = between / 60; // 小时
        if (between < 24) {
            return between + "小时前";
        }

        between = between / 24; // 天
        if (between < 30) {
            return between + "天前";
        }
        between = between / 30;
        if (between < 12) {
            return between + "个月前";
        }
        between = between / 12;
        return between + "年前";
    }


    /**
     * 计算两个时间点之间的友好时间差显示
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 友好的时间差字符串表示
     */
    public static String getTimeDiff(Date startTime, Date endTime) {
        return DateUtil.formatBetween(startTime, endTime, BetweenFormatter.Level.SECOND);
    }


    /**
     * 计算两个long类型的时间相差的时间，并转换为人类方便读的文本
     *
     * @param startTime 开始时间戳（毫秒）
     * @param endTime   结束时间戳（毫秒）
     * @return String 友好的时间间隔信息
     */
    public static String getTimeDiff(long startTime, long endTime) {
        return DateUtil.formatBetween(endTime - startTime, BetweenFormatter.Level.SECOND);
    }


}
