package LinearRegression;

import Utils.LinearRegressionUtil;

/**
 * 简单线性回归
 */
public class SimpleLinear {

    public static void main(String[] args) {
        double[] x1 = {1, 3, 2, 1, 3};
        double[] x2 = {14, 24, 18, 17, 27};
        // System.out.println(getB1(x1,x2));
    }

    /**
     * 求线性方程
     */
    public String getSimpleLinearEquation(double[] x, double[] y) {
        double numerator = 0;
        double denominator = 0;
        double xAverages = LinearRegressionUtil.getAverage(x);//x平均数
        double yAverages = LinearRegressionUtil.getAverage(y);//y平均数
        for (int i = 0; i < x.length; i++) {
            numerator += (x[i] - xAverages) * (y[i] - yAverages);
            denominator += Math.pow(x[i] - xAverages, 2);
        }

        double b1 = numerator / denominator;
        double b0 = yAverages - b1 * xAverages;
        if (b0 >= 0) {
            return "y=" + b1 + "+" + b0 + "x";
        } else {
            return "y=" + b1 + "" + b0 + "x";
        }
    }
}
