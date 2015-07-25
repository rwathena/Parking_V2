/* 
 * Copyright (C) 2015 Alex. 
 * All Rights Reserved.
 *
 * ALL RIGHTS ARE RESERVED BY Alex. ACCESS TO THIS
 * SOURCE CODE IS STRICTLY RESTRICTED UNDER CONTRACT. THIS CODE IS TO
 * BE KEPT STRICTLY CONFIDENTIAL.
 *
 * UNAUTHORIZED MODIFICATION OF THIS FILE WILL VOID YOUR SUPPORT CONTRACT
 * WITH Alex(zeroapp@126.com). IF SUCH MODIFICATIONS ARE FOR THE PURPOSE
 * OF CIRCUMVENTING LICENSING LIMITATIONS, LEGAL ACTION MAY RESULT.
 */

package com.zeroapp.utils;

import java.util.Arrays;


/**
 * <p>
 * Title: GraphTool.
 * </p>
 * <p>
 * Description: 用于点与多边形位置关系的判断.
 * </p>
 * 
 * @author Alex(zeroapp@126.com) 2015-6-8.
 * @version $Id$
 */

public class GraphTool {

    /**
     * 地球半径,单位m
     */
    private static final double EARTH_RADIUS = 6378137.0;

    /**
     * <p>
     * Title: isPointInPolygon.
     * </p>
     * <p>
     * Description: 判断点是否在多边形内(基本思路是用交点法).
     * </p>
     * 
     * @param point
     * @param boundaryPoints
     * @return
     */
    public static boolean isPointInPolygon(BmapPoint point, BmapPoint[] boundaryPoints) {
        // 防止第一个点与最后一个点相同
        if (boundaryPoints != null && boundaryPoints.length > 0 && boundaryPoints[boundaryPoints.length - 1].equals(boundaryPoints[0])) {
            boundaryPoints = Arrays.copyOf(boundaryPoints, boundaryPoints.length - 1);
        }
        int pointCount = boundaryPoints.length;

        // 首先判断点是否在多边形的外包矩形内，如果在，则进一步判断，否则返回false
        if (!isPointInRectangle(point, boundaryPoints)) {
            return false;
        }

        // 如果点与多边形的其中一个顶点重合，那么直接返回true
        for (int i = 0; i < pointCount; i++) {
            if (point.equals(boundaryPoints[i])) {
                return true;
            }
        }

        /**
         * 基本思想是利用X轴射线法，计算射线与多边形各边的交点，如果是偶数，则点在多边形外，否则在多边形内。还会考虑一些特殊情况，如点在多边形顶点上
         * ， 点在多边形边上等特殊情况。
         */
        // X轴射线与多边形的交点数
        int intersectPointCount = 0;
        // X轴射线与多边形的交点权值
        float intersectPointWeights = 0;
        // 浮点类型计算时候与0比较时候的容差
        double precision = 2e-10;
        // 边P1P2的两个端点
        BmapPoint point1 = boundaryPoints[0], point2;
        // 循环判断所有的边
        for (int i = 1; i <= pointCount; i++) {
            point2 = boundaryPoints[i % pointCount];

            /**
             * 如果点的y坐标在边P1P2的y坐标开区间范围之外，那么不相交。
             */
            if (point.getLat() < Math.min(point1.getLat(), point2.getLat()) || point.getLat() > Math.max(point1.getLat(), point2.getLat())) {
                point1 = point2;
                continue;
            }

            /**
             * 此处判断射线与边相交
             */
            if (point.getLat() > Math.min(point1.getLat(), point2.getLat()) && point.getLat() < Math.max(point1.getLat(), point2.getLat())) {// 如果点的y坐标在边P1P2的y坐标开区间内
                if (point1.getLng() == point2.getLng()) {// 若边P1P2是垂直的
                    if (point.getLng() == point1.getLng()) {
                        // 若点在垂直的边P1P2上，则点在多边形内
                        return true;
                    } else if (point.getLng() < point1.getLng()) {
                        // 若点在在垂直的边P1P2左边，则点与该边必然有交点
                        ++intersectPointCount;
                    }
                } else {// 若边P1P2是斜线
                    if (point.getLng() <= Math.min(point1.getLng(), point2.getLng())) {// 点point的x坐标在点P1和P2的左侧
                        ++intersectPointCount;
                    } else if (point.getLng() > Math.min(point1.getLng(), point2.getLng()) && point.getLng() < Math.max(point1.getLng(), point2.getLng())) {// 点point的x坐标在点P1和P2的x坐标中间
                        double slopeDiff = 0.0d;
                        if (point1.getLat() > point2.getLat()) {
                            slopeDiff = (point.getLat() - point2.getLat()) / (point.getLng() - point2.getLng()) - (point1.getLat() - point2.getLat()) / (point1.getLng() - point2.getLng());
                        } else {
                            slopeDiff = (point.getLat() - point1.getLat()) / (point.getLng() - point1.getLng()) - (point2.getLat() - point1.getLat()) / (point2.getLng() - point1.getLng());
                        }
                        if (slopeDiff > 0) {
                            if (slopeDiff < precision) {// 由于double精度在计算时会有损失，故匹配一定的容差。经试验，坐标经度可以达到0.0001
                                // 点在斜线P1P2上
                                return true;
                            } else {
                                // 点与斜线P1P2有交点
                                intersectPointCount++;
                            }
                        }
                    }
                }
            } else {
                // 边P1P2水平
                if (point1.getLat() == point2.getLat()) {
                    if (point.getLng() <= Math.max(point1.getLng(), point2.getLng()) && point.getLng() >= Math.min(point1.getLng(), point2.getLng())) {
                        // 若点在水平的边P1P2上，则点在多边形内
                        return true;
                    }
                }
                /**
                 * 判断点通过多边形顶点
                 */
                if (((point.getLat() == point1.getLat() && point.getLng() < point1.getLng())) || (point.getLat() == point2.getLat() && point.getLng() < point2.getLng())) {
                    if (point2.getLat() < point1.getLat()) {
                        intersectPointWeights += -0.5;
                    } else if (point2.getLat() > point1.getLat()) {
                        intersectPointWeights += 0.5;
                    }
                }
            }
            point1 = point2;
        }

        if ((intersectPointCount + Math.abs(intersectPointWeights)) % 2 == 0) {// 偶数在多边形外
            return false;
        } else { // 奇数在多边形内
            return true;
        }
    }

    /**
     * <p>
     * Title: isPointInRectangle.
     * </p>
     * <p>
     * Description: 判断点是否在矩形内在矩形边界上，也算在矩形内(根据这些点，构造一个外包矩形).
     * </p>
     * 
     * @param point
     *            点对象
     * @param boundaryPoints
     *            矩形边界点
     * @return
     */
    public static boolean isPointInRectangle(BmapPoint point, BmapPoint[] boundaryPoints) {
        BmapPoint southWestPoint = getSouthWestPoint(boundaryPoints); // 西南角点
        BmapPoint northEastPoint = getNorthEastPoint(boundaryPoints); // 东北角点
        return (point.getLng() >= southWestPoint.getLng() && point.getLng() <= northEastPoint.getLng() && point.getLat() >= southWestPoint.getLat() && point.getLat() <= northEastPoint.getLat());

    }

    /**
     * <p>
     * Title: getSouthWestPoint.
     * </p>
     * <p>
     * Description: 根据这组坐标，画一个矩形，然后得到这个矩形西南角的顶点坐标.
     * </p>
     * 
     * @param vertexs
     * @return
     */
    private static BmapPoint getSouthWestPoint(BmapPoint[] vertexs) {
        double minLng = vertexs[0].getLng(), minLat = vertexs[0].getLat();
        for (BmapPoint bmapPoint : vertexs) {
            double lng = bmapPoint.getLng();
            double lat = bmapPoint.getLat();
            if (lng < minLng) {
                minLng = lng;
            }
            if (lat < minLat) {
                minLat = lat;
            }
        }
        return new BmapPoint(minLng, minLat);
    }

    /**
     * <p>
     * Title: getNorthEastPoint.
     * </p>
     * <p>
     * Description: 根据这组坐标，画一个矩形，然后得到这个矩形东北角的顶点坐标.
     * </p>
     * 
     * @param vertexs
     * @return
     */
    private static BmapPoint getNorthEastPoint(BmapPoint[] vertexs) {
        double maxLng = 0.0d, maxLat = 0.0d;
        for (BmapPoint bmapPoint : vertexs) {
            double lng = bmapPoint.getLng();
            double lat = bmapPoint.getLat();
            if (lng > maxLng) {
                maxLng = lng;
            }
            if (lat > maxLat) {
                maxLat = lat;
            }
        }
        return new BmapPoint(maxLng, maxLat);
    }

    /**
     * <p>
     * Title: getDistance.
     * </p>
     * <p>
     * Description: 计算两点的距离.
     * </p>
     * 
     * @param lat_a
     *            点a的纬度
     * @param lng_a
     *            点a的经度
     * @param lat_b
     *            点b的纬度
     * @param lng_b
     *            点b的经度
     * @return
     */
    public static double getDistance(double lat_a, double lng_a, double lat_b, double lng_b) {
        double radLat1 = (lat_a * Math.PI / 180.0);
        double radLat2 = (lat_b * Math.PI / 180.0);
        double a = radLat1 - radLat2;
        double b = (lng_a - lng_b) * Math.PI / 180.0;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        Log.d("s: " + s);
        return s;
    }

//    public double getDistance2(double lat_a, double lng_a, double lat_b, double lng_b) {
//        float[] results = new float[1];
//        Location.distanceBetween(lat_a, lng_a, lat_b, lng_b, results);
//        return results[0];
//    }
//
//    public double getDistance3(double lat_a, double lng_a, double lat_b, double lng_b) {
//        double theta = lng_a - lng_b;
//        double dist = Math.sin(deg2rad(lat_a)) * Math.sin(deg2rad(lat_b)) + Math.cos(deg2rad(lat_a)) * Math.cos(deg2rad(lat_b)) * Math.cos(deg2rad(theta));
//        dist = Math.acos(dist);
//        dist = rad2deg(dist);
//        double miles = dist * 60 * 1.1515;
//        double kilomiters = miles * 1.609344;
//        return kilomiters;
//    }
//
//    // 将角度转换为弧度
//    static double deg2rad(double degree) {
//        return degree / 180 * Math.PI;
//    }
//
//    // 将弧度转换为角度
//    static double rad2deg(double radian) {
//        return radian * 180 / Math.PI;
//    }
//
////    public double getDistance4(double lat_a, double lng_a, double lat_b, double lng_b) {
////        double d1 = 3.141592653589793D * (paramDouble2 - paramDouble1) / 180.0D;  
////        double d2 = 3.141592653589793D * (paramDouble4 - paramDouble3) / 180.0D;  
////        double d3 = Math.sin(d1 / 2.0D) * Math.sin(d1 / 2.0D) + Math.cos(3.141592653589793D * paramDouble1 / 180.0D) * Math.cos(3.141592653589793D * paramDouble2 / 180.0D) * Math.sin(d2 / 2.0D) * Math.sin(d2 / 2.0D);  
////        return 6371.0D * (2.0D * Math.atan2(Math.sqrt(d3), Math.sqrt(1.0D - d3)));  
////    }
//
//    /**
//     * <p>
//     * Title: 获取方位角.
//     * </p>
//     * <p>
//     * Description: TODO.
//     * </p>
//     * 
//     * @param lat_a
//     * @param lng_a
//     * @param lat_b
//     * @param lng_b
//     * @return
//     */
//    public double getAzimuth(double lat_a, double lng_a, double lat_b, double lng_b) {
//        double d = 0;
//        lat_a = lat_a * Math.PI / 180;
//        lng_a = lng_a * Math.PI / 180;
//        lat_b = lat_b * Math.PI / 180;
//        lng_b = lng_b * Math.PI / 180;
//        d = Math.sin(lat_a) * Math.sin(lat_b) + Math.cos(lat_a) * Math.cos(lat_b) * Math.cos(lng_b - lng_a);
//        d = Math.sqrt(1 - d * d);
//        d = Math.cos(lat_b) * Math.sin(lng_b - lng_a) / d;
//        d = Math.asin(d) * 180 / Math.PI;
//        // d = Math.round(d*10000);
//        return d;
//    }

}
