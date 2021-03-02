package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LastSaved {
    public static void lastSavedDataWrite(String input0,String input1,String input2,String input3,String input4,String input5){
        try {
            FileWriter myWriter=new FileWriter("SavedData.txt");
            myWriter.write(input0+"\n");
            myWriter.write(input1+"\n");
            myWriter.write(input2+"\n");
            myWriter.write(input3+"\n");
            myWriter.write(input4+"\n");
            myWriter.write(input5);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String[] lastSavedDataRead(){
        String[] inputList =new String[6];
        String input="";
        int i=0;
        try {
            File myObj = new File("C:\\Users\\mysterio_fahd\\IdeaProjects\\Assignment\\SavedData.txt");
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNextLine()){
                input=myReader.nextLine();
                inputList[i]=input;
                i++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return inputList;

    }
}
