package Utils;

public class LinearRegressionUtil {
    /**
     * 获取平均值
     */
    public static double getAverage(double[] arrays) {
        double count = 0;
        for (double array : arrays) {
            count += array;
        }
        return count / arrays.length;
    }

    /**
     * 获取二元协方差
     */
    private double getBinaryCovariance(double[] xArray, double[] yArray) {
        double cov = 0;
        double xAverage = getAverage(xArray);
        double yAverage = getAverage(yArray);
        for (int i = 0; i < xArray.length; i++) {
            cov += (xArray[i] - xAverage) * (yArray[i] - yAverage);
        }
        return cov;
    }

    /**
     * 获取多元协方差
     */
    public static double getCovariance(double[]... elements) {
        double cov = 0;
        double[] averages = new double[elements.length];//平均数数组
        int rows = elements.length;//列数
        for (int i = 0; i < rows; i++) {
            averages[i] = getAverage(elements[i]);
        }
        int col = elements[0].length;//行数
        for (int i = 0; i < col; i++) {
            double temp = 1;//用来存储(x[i]-xAverage)*(y[i]-yAverage)*(z[i]-zAverage)
            for (int j = 0; j < rows; j++) {
                temp *= (elements[i][j] - averages[j]);
            }
            cov += temp;
        }
        return cov;
    }

    /**
     * 获取单个方差
     */
    private double getVariance(double[] array) {
        double average = getAverage(array);
        double var = 0;
        for (int i = 0; i < array.length; i++) {
            var += (array[i] - average);
        }
        return var;
    }

    /**
     * 获取多个数组方差
     */
    public static double[] getVariance(double[]... arrays) {
        double[] vars = new double[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            double count = 0;
            double average = getAverage(arrays[i]);
            for (int j = 0; j < arrays[i].length; j++) {
                count += (arrays[i][j] - average);
            }
            vars[i] = count;
        }
        return vars;
    }

    /**
     * 求多个数组方差和
     *
     * @return
     */
    public static double getCountVariance(double[]... arrays) {
        double[] variance = getVariance(arrays);
        double count = 0;
        for (double element : variance) {
            count += element;
        }
        return count;
    }

    /**
     * 获取相关系数
     */
    public static double getCorrelationCoefficient(double[]... arrays) {
        return getCovariance(arrays) / getCountVariance();
    }

}
