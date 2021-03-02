package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class History {
    public static void simpleSavings(double iDeposit,double rate,double time,double fValue){
        try {
            FileWriter myWriter=new FileWriter("history.txt",true);
            myWriter.write("Initial Deposit= "+iDeposit+"\n");
            myWriter.write("Interest Rate= "+rate+"\n");
            myWriter.write("Time= "+time+"\n");
            myWriter.write("Future Value= "+fValue+"\n\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void compoundSavings(double iDeposit,double rate,double time,double mDeposit,double fValue){
        try {
            FileWriter myWriter=new FileWriter("history.txt",true);
            myWriter.write("Initial Deposit= "+iDeposit+"\n");
            myWriter.write("Interest Rate= "+rate+"\n");
            myWriter.write("Time= "+time+"\n");
            myWriter.write("Monthly Deposit= "+mDeposit+"\n");
            myWriter.write("Future Value= "+fValue+"\n\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loan(double lAmount,double rate,double time,double mPayment){
        try {
            FileWriter myWriter=new FileWriter("history.txt",true);
            myWriter.write("Loan Amount= "+lAmount+"\n");
            myWriter.write("Interest Rate= "+rate+"\n");
            myWriter.write("Loan Term= "+time+"\n");
            myWriter.write("Monthly Payment= "+mPayment+"\n\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mortgage(double pAmount,double rate,double time,double mPayment){
        try {
            FileWriter myWriter=new FileWriter("history.txt",true);
            myWriter.write("Principal Amount= "+pAmount+"\n");
            myWriter.write("Interest Rate= "+rate+"\n");
            myWriter.write("Loan Term= "+time+"\n");
            myWriter.write("Monthly Payment= "+mPayment+"\n\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<String> historyRead(){
        ArrayList<String> outputList=new ArrayList<>();
        String output="";
        int i=0;
        try {
            File myObj = new File("C:\\Users\\mysterio_fahd\\IdeaProjects\\Assignment\\history.txt");
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNextLine()){
                output=myReader.nextLine();
                outputList.add(output);
                i++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return outputList;
    }
}
