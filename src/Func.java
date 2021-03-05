import static java.lang.Math.abs;

public class Func {
    private double x3, x2, x1, k, a, b, eps;

    public Func (double x3, double x2, double x1, double k, double a, double b, double eps) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.k = k;
        this.a = a;
        this.b = b;
        this.eps = eps;
    }

    public Func () {
        this.x1 = -17.37;
        this.x2 = 4.81;
        this.x3 = 1;
        this.k = 5.38;
        this.a = 0;
        this.b = 3;
        this.eps = 0.01;
    }

    public String func1(){
        double c, x = 0;
        int n = 0;
        int count = 0;
        String answer = "";
        double a = this.a, b=this.b;

        while ( abs(a-b) > eps) {
            c = (a+b)/2;
            n += 1;
            if (f(a) * f(c)<= 0) b = c;
            else
            {
                a = c;
                x = (a+b)/2;
            }
            answer += ++count + ". n = " + n +"; a = "+ a + "; b =" + b + "; x = " + x + "; f(a) = " + f(a) + "; f(b) = " + f(b) + "; f(x) = " + f(c) + "; abs(a-b) = " + abs(a-b) + "\n";
        }

        answer += "x = " + x + " f(x) = " + f(x) + " n = " + n + "\n";

        return answer;
    }

    public String func3(){
        String answer = "";
        int count = 0;
        double tmp_1 = this.b, tmp_2, eps = 0.01f, tmp = tmp_1 ;

        tmp_2 = tmp_1 - (f(tmp_1)/f1(tmp_1)); //первое вхождение
        answer += ++count + ". " + "xi-1 = " + tmp_1 + "; xi = " + tmp_2 + "; \n";

        while(abs(tmp-tmp_2) > eps && (f(tmp)/f1(tmp)) > eps && f(tmp) > eps) {
            answer += ++count + ". " + "xi-1 = " + tmp_1 + "; xi = " + tmp_2 + "; \n";
            tmp = tmp_1;
            tmp_1 = tmp_2;
            tmp_2 = tmp - (f(tmp) / f1(tmp));
        }

        answer += "Ответ: " + tmp;

        return answer;
    }

    public String func5(){
        double MIN_RANGE = a;
        double MAX_RANGE = b;
        double EPS = eps;
        String answer = "";

        double x0, x = 5;
        double fx;
        int count = 0;
        x = searchX(MIN_RANGE, MAX_RANGE, x);
        double lambda = getLambda(x);
        do {
            x0 = x;
            x = x - lambda * (Math.pow(x, 3) + x2 * Math.pow(x, 2) + x1 * x + k);
            fx = Math.pow(x0, 3) + x2 * Math.pow(x0, 2) + x1 * x0 + k;
            answer += ++count + ". " + "x0 = " + x0 + "; fx = " + fx + "; \n";
        } while (Math.abs(x - x0) >= EPS);

        answer+="Ответ: " + x0;

        return answer;
    }

    private double f(double x){
        return this.x3*Math.pow(x, 3) + this.x2*x + this.x1 + k;
    }

    private double f1(double x){
        return 3*this.x3*Math.pow(x, 2) + 2*this.x2*x + this.x1;
    }

    private double searchX(double minRange, double maxRange, double x) {
        double a = 3*x3 * Math.pow(minRange, 2) - 2*x2 * minRange - x1;
        double b = 3*x3 * Math.pow(maxRange, 2) - 2*x2 * maxRange - x1 ;
        double c = 3*x3 * Math.pow(x, 2) - 2*x2 * x - x1;
        return a >= b && a >= c ? minRange : b >= a && b >= c ? maxRange : x;
    }

    private double getLambda(double x) {
        return 1. / (3*x3 * Math.pow(x, 2) - 2*x2 * x - x1);
    }
}
