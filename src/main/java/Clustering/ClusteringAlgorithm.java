package Clustering;

import Utils.MeasureUtil;

import java.util.Arrays;

public class ClusteringAlgorithm {

    /**
     * @param data      数据源
     * @param k         k个聚类中心点
     * @param threshold 阈值
     * @return
     */
    public static double[][] Kmeans(double[][] data, double[][] k, int threshold) {
        double[][] kmeans = new double[data.length][data[0].length + 1];
        Arrays.fill(kmeans, 0);//填充 0
        kmeans = copyOf(data, kmeans);
        //遍历每一行元素
        int rows = data.length;
        int column = data[0].length;
        for (int i = 0; i < rows; i++) {
            //每一行元素和k中的点进行度量
            double distance = 0;
            for (int j = 1; j < k.length; j++) {
                //找出所属的类
                distance = getDistance(data[i], k[j]) - distance > 0 ? 0 : j;
            }
            //给最后一列填充
            kmeans[i][column + 1] = distance;
        }
        //重新计算中心点
        return null;
    }


    public static double[][] copyOf(double[][] from, double[][] to) {
        for (int i = 0; i < from.length; i++) {
            for (int j = 0; j < from[i].length; j++) {
                to[i][j] = from[i][j];
            }
        }
        return to;
    }

    public static double getDistance(double[] x, double[] y) {
        return MeasureUtil.getCanberraDistance(x, y);
    }
}
