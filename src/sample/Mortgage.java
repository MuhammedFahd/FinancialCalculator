package sample;

public class Mortgage {
    public static double rePayment(double pAmount,double rate,double time){
        rate=rate/100;
        double output=(pAmount*(rate/12)*Math.pow(1+(rate/12),12*time))/(Math.pow(1+(rate/12),12*time)-1);
        return output;
    }
    public static double time(double pAmount,double rate,double mPayment){
        rate=rate/100;
        double output=(Math.log(mPayment/(mPayment-(pAmount*rate/12))))/(Math.log(1+(rate/12)));
        return output/12;
    }
    public static double noOfPayments(double time){
        return time*12;
    }
    public static double principalAmount(double rate,double time,double mPayment){
        rate=rate/100;
        double output=mPayment*(Math.pow(1+(rate/12),12*time)-1)/((rate/12)*Math.pow(1+(rate/12),12*time));
        return output;
    }
}
