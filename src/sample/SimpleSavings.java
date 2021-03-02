package sample;

public class SimpleSavings {
    public static double futureValue(double pAmount,double rate,double time){
        rate=rate/100;
        double output=pAmount*Math.pow((1+(rate/12)),(12*time));
        return output;
    }
    public static double interestRate(double pAmount,double fValue,double time){
        double output=12*(Math.pow((fValue/pAmount),(1/(12*time)))-1);
        return output;
    }
    public static double principalAmount(double fValue,double rate,double time){
        rate=rate/100;
        double output=fValue/Math.pow((1+rate/12),(12*time));
        return output;
    }
    public static double time(double pAmount,double fValue,double rate){
        rate=rate/100;
        double output=Math.log(fValue/pAmount)/(12*(Math.log(1+rate/12)));
        return output;
    }
}
