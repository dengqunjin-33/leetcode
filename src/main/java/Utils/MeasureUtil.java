package Utils;

public class MeasureUtil {

    /**
     * 欧式距离
     */
    public static double getEuclideanDistance(double[] x, double[] y) {
        double distance = 0;
        for (int i = 0; i < x.length; i++) {
            distance += Math.pow(x[i] - y[i], 2);
        }
        distance = Math.sqrt(distance);
        return distance;
    }

    /**
     * 闵可夫斯基距离
     */
    public static double getLPDistance(double[] x, double[] y, double p) {
        double distance = 0;
        for (int i = 0; i < x.length; i++) {
            distance += Math.pow(Math.abs(x[i] - y[i]), p);
        }
        distance = Math.pow(distance, 1 / p);
        return distance;
    }

    /**
     * 曼哈顿距离
     */
    public static double getManhattanDistance(double[] x, double[] y) {
        double distance = 0;
        for (int i = 0; i < x.length; i++) {
            distance += Math.abs(x[i] - y[i]);
        }
        return distance;
    }

    /**
     * 棋盘距离
     */
    public static double getCheckerboardDistance(double[] x, double[] y) {
        double distance = Math.abs(x[0] - y[0]);
        for (int i = 1; i < x.length; i++) {
            distance = Math.abs(x[i] - y[i]) - distance > 0 ? Math.abs(x[i] - y[i]) : distance;
        }
        return distance;
    }

    /**
     * 堪培拉距离
     */
    public static double getCanberraDistance(double[] x, double[] y) {
        double distance = 0;
        for (int i = 0; i < x.length; i++) {
            distance += Math.abs(x[i] - y[i]) / (Math.abs(x[i]) + Math.abs(y[i]));
        }
        return distance;
    }
}
