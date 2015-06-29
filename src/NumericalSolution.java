package prob;

import java.util.function.*;

/**
 * Created by FickleFox on 2015/06/29.
 */
public class NumericalSolution {

    //各種線形方程式の数値解析

    //関数f(x)
    Function<Double,Double> f;
    Function<Double,Double> df;

    double eps;
    int maxCount;
    int count;
    double result;

    NumericalSolution(Function<Double,Double> f,Function<Double,Double>df)
    {
        this.f=f;
        this.df=df;
        eps=10E-10;
        maxCount=300;
        count=0;
        result=0;
    }

    NumericalSolution(Function<Double,Double> f,Function<Double,Double>df,double eps)
    {
        this.f=f;
        this.df=df;
        this.eps=eps;
        maxCount=300;
        count=0;
        result=0;
    }

    NumericalSolution(Function<Double,Double> f,Function<Double,Double>df,double eps,int maxCount)
    {
        this.f=f;
        this.df=df;
        this.eps=eps;
        this.maxCount=maxCount;
        count=0;
        result=0;
    }

    public int getCount()
    {
        return count;
    }
    public double getResult() {return result;}

    //絶対誤差
    public double AbsErr(double alpha)
    {
        return Math.abs((result-alpha)/result);
    }

    public double AbsErr(double[] alpha)
    {
        double x=Math.abs(result-(alpha[0])/result);
        for (int i = 0; i < alpha.length; i++) {
            x=Math.min(x,Math.abs(result-(alpha[i])/result));
        }
        return x;
    }

    public boolean Relative(double x1,double x2)
    {
        return Math.abs((x2-x1)/x2)>eps;
    }

    public boolean Residue(double x1)
    {
        return Math.abs(f.apply(x1))>eps;
    }

    //Newton残差
    public boolean NewtonResidue(double x0) {
        Function<Double,Double> funcN = xk-> xk-f.apply(xk)/df.apply(xk);

        count=0;
        double x1=x0;
        double x2=funcN.apply(x1);
        while(Residue(x1)) {
            x1 = x2;
            x2 = funcN.apply(x1);
            count++;
            if (count>maxCount)
            {
                return false;
            }
        }
        result=x2;
        return true;
    }

    //Newton相対誤差
    public boolean NewtonRelative(double x0) {
        Function<Double,Double> funcN = xk-> xk-f.apply(xk)/df.apply(xk);

        count=0;
        double x1=x0;
        double x2=funcN.apply(x1);
        while(Relative(x1, x2)) {
            x1 = x2;
            x2 = funcN.apply(x1);
            count++;
            if (count>maxCount)
            {
                return false;
            }
        }
        result=x2;
        return true;
    }

    public boolean SecantResidue(double x0)
    {
        BiFunction<Double,Double,Double> funcS = (xk, xk1)-> xk-(f.apply(xk)*((xk - xk1)/(f.apply(xk)-f.apply(xk1))));

        count=1;
        double x1=x0+1.0;
        double x2=funcS.apply(x1, x0);
        double x3=funcS.apply(x2, x1);
        while(Residue(x2)) {
            x1 = x2;
            x2 = x3;
            x3 = funcS.apply(x2, x1);
            count++;
            if (count > maxCount) {
                return false;
            }
        }
        result=x3;
        return true;
    }

    public boolean SecantRelative(double x0)
    {
        BiFunction<Double,Double,Double> funcS = (xk, xk1)-> xk-(f.apply(xk)*((xk - xk1)/(f.apply(xk)-f.apply(xk1))));

        count=1;
        double x1=x0+1.0;
        double x2=funcS.apply(x1, x0);
        double x3=funcS.apply(x2, x1);
        while(Relative(x2, x3)) {
            x1 = x2;
            x2 = x3;
            x3 = funcS.apply(x2, x1);
            count++;
            if (count > maxCount) {
                return false;
            }
        }
        result=x3;
        return true;
    }


  public boolean ParallelChordResidue(double x0)
    {
        Function<Double,Double> funcP= xk-> xk-(f.apply(xk)/f.apply(x0));

        count=0;
        double x1=funcP.apply(x0);
        double x2=funcP.apply(x1);
        while(Residue(x1)) {
            x1 = x2;
            x2 = funcP.apply(x1);
            count++;
            if (count>maxCount)
            {
                return false;
            }
        }
        result =x2;
        return true;
    }

    public boolean ParallelChordRelative(double x0)
    {
        Function<Double,Double> funcP= xk-> xk-(f.apply(xk)/f.apply(x0));

        count=0;
        double x1=funcP.apply(x0);
        double x2=funcP.apply(x1);
        while(Relative(x1, x2)) {
            x1 = x2;
            x2 = funcP.apply(x1);
            count++;
            if (count>maxCount)
            {
                return false;
            }
        }
        result=x2;
        return true;
    }

}
