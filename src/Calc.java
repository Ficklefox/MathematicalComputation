package prob;

import java.lang.reflect.Array;
import java.util.Map;

/**
 * Created by Onodera on 2015/06/08.
 */
public class Calc {

    //行列・ベクトルのコンソール出力

    public static void printVec(double x[]) {
        System.out.println("(" + x[0] + "," + x[1] + ")");
    }

    public static void printMat(double A[][]) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }

    //ベクトルに関する演算

    public static double[] scalarMultiple(double c, double x[]) {
        double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            result[i] = c * x[i];
        }
        return result;
    }

    public static double[] addVec(double x[], double y[]) {
        if (x.length != y.length) {
            return null;
        }

        double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            result[i] = x[i] + y[i];
        }
        return result;
    }

    public static double[] subVec(double x[], double y[]) {
        if (x.length != y.length) {
            return null;
        }

        double[] result = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            result[i] = x[i] - y[i];
        }
        return result;
    }

    public static double innProd(double x[], double y[]) {
        if (x.length != y.length) {
            return 0;
        }

        double result = 0;
        for (int i = 0; i < x.length; i++) {
            result += x[i] * y[i];
        }
        return result;
    }

    //行列・ベクトルに関する演算
    public static double[] matVec(double A[][], double x[])
    {
        if (x.length != A[0].length) {
            return null;
        }

        double[] result = new double[x.length];
        for (int i = 0; i < A.length; i++) {
            result[i]=0;
            for (int j = 0; j < A[i].length; j++) {
                result[i]+=A[i][j]*x[j];
            }
        }
        return result;
    }

    public static double[] residual(double A[][], double x[],double b[])
    {
        if (x.length != A[0].length||b.length!=A[0].length) {
            return null;
        }

        double[] result = new double[x.length];
        for (int i = 0; i < A.length; i++) {
            result[i]=0;
            for (int j = 0; j < A[i].length; j++) {
                result[i]+=A[i][j]*x[j];
            }
        }
        return subVec(result,b);
    }

    //行列同士の演算
    public static double[][] addMat(double A[][], double B[][])
    {

        if (A.length!=B.length)
        {
            return null;
        }

        double[][] result = new double[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            if (A[i].length!=B[i].length)
            {
                return null;
            }
            for (int j = 0; j < A[i].length; j++) {
                result[i][j]=A[i][j]+B[i][j];
            }
        }
        return result;
    }

    public static double[][] multipleMat(double A[][], double B[][])
    {

        for (int i = 0; i < A.length; i++) {
            if (A[i].length!=B.length)
            {
                return null;
            }
        }

        double[][] result = new double[A.length][B[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                result[i][j]=0;
                for (int k = 0; k < B.length; k++) {
                    result[i][j]+=A[i][k]*B[k][j];
                }
            }
        }

        return result;
    }

    //ベクトルノルム
    public static double vecNorm1(double x[])
    {
        double result=0;

        for (int i = 0; i < x.length; i++) {
            result+=Math.abs(x[i]);
        }
        return result;
    }

    public static double vecNorm2(double x[])
    {
        double result=0;

        for (int i = 0; i < x.length; i++) {
            result+= Math.pow(Math.abs(x[i]), 2);
        }

        return Math.sqrt(result);
    }

    public static double vecNormInf(double x[])
    {
        double result=x[0];

        for (int i = 0; i < x.length; i++) {
            result=Math.max(result, Math.abs(x[i]));
        }
        return result;
    }

    //行列ノルム
    public static double matNorm1(double A[][])
    {
        double[] result = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            result[i]=0;
            for (int j = 0; j < A[i].length; j++) {
                result[i]+=A[i][j];
            }
        }
        return vecNormInf(result);
    }

    public static double matNormInf(double A[][])
    {
        for (int i = 0; i < A.length; i++) {
            if (A[0].length!=A[i].length)
            {
                return 0;
            }
        }
        double[] result = new double[A[0].length];
        for (int i = 0; i < result.length; i++) {
            result[i]=0;
        }

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                result[j]+=A[i][j];
            }
        }
        return vecNormInf(result);
    }

    //

}
