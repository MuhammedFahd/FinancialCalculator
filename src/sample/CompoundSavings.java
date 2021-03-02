package sample;

public class CompoundSavings {
    public static double futureValue(double pAmount,double rate,double time,double mDeposit){
        rate=rate/100;
        double compoundInterest=pAmount*Math.pow((1+(rate/12)),(12*time));
        double output=mDeposit*((Math.pow((1+(rate/12)),(12*time))-1)/(rate/12));
        output=output+compoundInterest;
        return output;
    }
    public static double time(double pAmount,double fValue,double rate,double mDeposit){
        rate=rate/100;
        double output=Math.log((fValue+(mDeposit*12/rate))/(pAmount+(mDeposit*12/rate)))/(Math.log(1+(rate/12))*12);
        return output;
    }
    public static double monthlyDeposit(double pAmount,double fValue,double rate,double time){
        rate=rate/100;
        double compoundInterest=pAmount*Math.pow((1+(rate/12)),(12*time));
        double output=(fValue-compoundInterest)/(((Math.pow((1+(rate/12)),(12*time))-1)/(rate/12)));
        return output;
    }
    public static double principalAmount(double fValue,double rate,double time,double mDeposit){
        rate=rate/100;
        double output=(fValue-mDeposit*((Math.pow(1+(rate/12),12*time)-1)/(rate/12)))/(Math.pow(1+(rate/12),12*time));
        return output;
    }
}
