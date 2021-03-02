package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main extends Application {
    //creating a global variable to check the select text field
    public static int selectedTxtField;


    @Override
    public void start(Stage primaryStage) throws Exception{
        //creating the homepage
        primaryStage.setTitle("Financial Calculator");
        Label lblHeading=new Label("WELCOME!");
        lblHeading.setLayoutX(119);
        lblHeading.setLayoutY(40);
        lblHeading.setId("header");  //adding CSS to the label
        Label lblName=new Label("Name");
        lblName.setLayoutX(51);
        lblName.setLayoutY(111);
        lblName.setId("name"); //adding CSS to the label

        TextField txtName=new TextField();
        txtName.setPromptText("enter your name");
        txtName.setLayoutX(105);
        txtName.setLayoutY(110);


        Button btnProceed=new Button("Proceed");
        btnProceed.setLayoutX(210);
        btnProceed.setLayoutY(213);
        btnProceed.setId("proceed"); //adding CSS to the button

        Button btnExit=new Button("Exit");
        btnExit.setLayoutX(41);
        btnExit.setLayoutY(213);
        btnExit.setId("exit"); //adding CSS to the button

        //adding a background image for the homepage window
        Image img=new Image("home.jpg");
        ImageView imgView=new ImageView(img);
        imgView.setFitWidth(325);
        imgView.setFitHeight(300);

        //Scene creation and adding elements to it
        Pane root=new Pane();
        root.getChildren().add(imgView);
        root.getChildren().add(lblHeading);
        root.getChildren().add(lblName);
        root.getChildren().add(txtName);
        root.getChildren().add(btnProceed);
        root.getChildren().add(btnExit);
        primaryStage.setScene(new Scene(root, 325, 300));
        root.getStylesheets().add(getClass().getResource("homepage.css").toExternalForm()); //css reference
        primaryStage.show();

        //creating the calculator window when proceed button is clicked
        btnProceed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage home=(Stage) btnProceed.getScene().getWindow();
                home.close();

                String name=txtName.getText();
                Alert a=new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Hello "+name+"! welcome to our financial calculator");
                a.show();

                Stage calc=new Stage();
                calc.setTitle("Financial Calculator");

                //adding splitpane to divide the screen into two horizontally
                SplitPane sPane=new SplitPane();

                //Tabpane is created to add 5 different tabs
                TabPane upSide=new TabPane();
                upSide.setPrefWidth(398);
                upSide.setPrefHeight(323);

                //tab1 is created for the simple savings calculator
                Tab tab1=new Tab("Simple Savings");
                tab1.setId("tab1");
                AnchorPane p1=new AnchorPane();
                p1.setPrefWidth(200);
                p1.setPrefHeight(180);
                tab1.setContent(p1);

                //adding a background image for simple savings calculator
                Image img1=new Image("savings.jpg");
                ImageView imgView1=new ImageView(img1);
                imgView1.setFitWidth(400);
                imgView1.setFitHeight(300);

                //creation of all the labels needed for the simple savings calculator
                Label lbl1Tab1=new Label("Initial Deposit");
                lbl1Tab1.setLayoutX(37);
                lbl1Tab1.setLayoutY(29);
                Label lbl2Tab1=new Label("Interest Rate");
                lbl2Tab1.setLayoutX(37);
                lbl2Tab1.setLayoutY(64);
                Label lbl3Tab1=new Label("Time");
                lbl3Tab1.setLayoutX(37);
                lbl3Tab1.setLayoutY(98);
                Label lbl4Tab1=new Label("Future Value");
                lbl4Tab1.setLayoutX(37);
                lbl4Tab1.setLayoutY(135);

                //creation of all the textfields needed for the simple savings calculator
                TextField txt1Tab1=new TextField();
                txt1Tab1.setLayoutX(148);
                txt1Tab1.setLayoutY(25);
                txt1Tab1.setPromptText("$");
                TextField txt2Tab1=new TextField();
                txt2Tab1.setLayoutX(148);
                txt2Tab1.setLayoutY(60);
                txt2Tab1.setPromptText("%");
                TextField txt3Tab1=new TextField();
                txt3Tab1.setLayoutX(148);
                txt3Tab1.setLayoutY(94);
                txt3Tab1.setPromptText("years");
                TextField txt4Tab1=new TextField();
                txt4Tab1.setLayoutX(148);
                txt4Tab1.setLayoutY(131);
                txt4Tab1.setPromptText("$");


                //creating help button and calculate button for simple savings calculator
                Button btn1Tab1=new Button("Help");
                btn1Tab1.setLayoutX(61);
                btn1Tab1.setLayoutY(200);
                btn1Tab1.setId("help");
                Button btn2Tab1=new Button("Calculate");
                btn2Tab1.setLayoutX(223);
                btn2Tab1.setLayoutY(200);
                btn2Tab1.setId("calculate");

                //adding the elements to the anchor pane of tab1
                p1.getChildren().addAll(imgView1,lbl1Tab1,lbl2Tab1,lbl3Tab1,lbl4Tab1,txt1Tab1,txt2Tab1,txt3Tab1,txt4Tab1,btn1Tab1,btn2Tab1);

                //help overview of simple savings is shown when the help button is pressed
                btn1Tab1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Alert info=new Alert(Alert.AlertType.INFORMATION);
                        info.setContentText("This is a savings calculator with compounding interest.There are 4 fields and you can calculate any of the field by entering data for the other fields and keeping the relevant field empty!!!");
                        info.setHeaderText("ABOUT");
                        info.show();
                    }
                });


                //calculations of simple savings are done when button calculate is pressed
                btn2Tab1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //variables created for each parameter
                        double iDeposit;
                        double rate;
                        double time;
                        double fValue;
                        double output;
                        DecimalFormat df=new DecimalFormat("####.#");
                        Alert a=new Alert(Alert.AlertType.WARNING);

                        //checking whether the textfield for initial deposit is empty
                        //then finding the initial deposit based on other parameters
                        if(txt1Tab1.getText().isEmpty()){
                            try {
                                rate = Double.parseDouble(txt2Tab1.getText());
                                time = Double.parseDouble(txt3Tab1.getText());
                                fValue = Double.parseDouble(txt4Tab1.getText());
                                output = SimpleSavings.principalAmount(fValue, rate, time); //calling the method to calculate
                                txt1Tab1.setText("" + df.format(output));
                                History.simpleSavings(output,rate,time,fValue); //calling the method to write data into the history file
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!");
                                a.show();
                            }
                            //checking whether the textfield for rate is empty
                            //then finding the rate based on other parameters
                        }else if(txt2Tab1.getText().isEmpty()){
                            try {
                                iDeposit = Double.parseDouble(txt1Tab1.getText());
                                time = Double.parseDouble(txt3Tab1.getText());
                                fValue = Double.parseDouble(txt4Tab1.getText());
                                output = SimpleSavings.interestRate(iDeposit, fValue, time);  //calling the method to calculate
                                txt2Tab1.setText("" + df.format(output * 100));
                                History.simpleSavings(iDeposit,output*100,time,fValue);  //calling the method to write data into the history file
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!!!!");
                                a.show();
                            }
                            //checking whether the textfield for time is empty
                            //then finding the time based on other parameters
                        }else if(txt3Tab1.getText().isEmpty()){
                            try {
                                iDeposit = Double.parseDouble(txt1Tab1.getText());
                                fValue = Double.parseDouble(txt4Tab1.getText());
                                rate = Double.parseDouble(txt2Tab1.getText());
                                output = SimpleSavings.time(iDeposit, fValue, rate); //calling the method to calculate
                                txt3Tab1.setText("" + df.format(output));
                                History.simpleSavings(iDeposit,rate,output,fValue);  //calling the method to write data into the history file
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!");
                                a.show();
                            }
                            //checking whether the textfield for future value is empty
                            //then finding the future value based on other parameters
                        }else if(txt4Tab1.getText().isEmpty()){
                            try {
                                iDeposit = Double.parseDouble(txt1Tab1.getText());
                                rate = Double.parseDouble(txt2Tab1.getText());
                                time = Double.parseDouble(txt3Tab1.getText());
                                output = SimpleSavings.futureValue(iDeposit, rate, time);  //calling the method to calculate
                                txt4Tab1.setText("" + df.format(output));
                                History.simpleSavings(iDeposit,rate,time,output); //calling the method to write data into the history file
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!");
                                a.show();
                            }
                        }

                    }
                });



                //tab2 is created for the compound savings calculator
                Tab tab2=new Tab("Compound Savings");
                tab2.setId("tab2");
                AnchorPane p2=new AnchorPane();
                p2.setPrefWidth(200);
                p2.setPrefHeight(180);
                tab2.setContent(p2);

                //adding a background image for compound savings calculator
                Image img2=new Image("savings.jpg");
                ImageView imgView2=new ImageView(img2);
                imgView2.setFitWidth(400);
                imgView2.setFitHeight(300);

                //creation of all the labels needed for the compound savings calculator
                Label lbl1Tab2=new Label("Initial Deposit");
                lbl1Tab2.setLayoutX(37);
                lbl1Tab2.setLayoutY(29);
                Label lbl2Tab2=new Label("Interest Rate");
                lbl2Tab2.setLayoutX(37);
                lbl2Tab2.setLayoutY(64);
                Label lbl3Tab2=new Label("Time");
                lbl3Tab2.setLayoutX(37);
                lbl3Tab2.setLayoutY(98);
                Label lbl4Tab2=new Label("Monthly Deposit");
                lbl4Tab2.setLayoutX(37);
                lbl4Tab2.setLayoutY(139);
                Label lbl5Tab2=new Label("Future Value");
                lbl5Tab2.setLayoutX(37);
                lbl5Tab2.setLayoutY(177);

                //creation of all the textfields needed for the compound savings calculator
                TextField txt1Tab2=new TextField();
                txt1Tab2.setLayoutX(148);
                txt1Tab2.setLayoutY(25);
                txt1Tab2.setPromptText("$");
                TextField txt2Tab2=new TextField();
                txt2Tab2.setLayoutX(148);
                txt2Tab2.setLayoutY(60);
                txt2Tab2.setPromptText("%");
                TextField txt3Tab2=new TextField();
                txt3Tab2.setLayoutX(148);
                txt3Tab2.setLayoutY(94);
                txt3Tab2.setPromptText("years");
                TextField txt4Tab2=new TextField();
                txt4Tab2.setLayoutX(148);
                txt4Tab2.setLayoutY(135);
                txt4Tab2.setPromptText("$");
                TextField txt5Tab2=new TextField();
                txt5Tab2.setLayoutX(148);
                txt5Tab2.setLayoutY(173);
                txt5Tab2.setPromptText("$");

                //creating help button and calculate button for compound savings calculator
                Button btn1Tab2=new Button("Help");
                btn1Tab2.setLayoutX(61);
                btn1Tab2.setLayoutY(239);
                btn1Tab2.setId("help");
                Button btn2Tab2=new Button("Calculate");
                btn2Tab2.setLayoutX(223);
                btn2Tab2.setLayoutY(239);
                btn2Tab2.setId("calculate");

                //adding the elements to the anchor pane of tab2
                p2.getChildren().addAll(imgView2,lbl1Tab2,lbl2Tab2,lbl3Tab2,lbl4Tab2,lbl5Tab2,txt1Tab2,txt2Tab2,txt3Tab2,txt4Tab2,txt5Tab2,btn1Tab2,btn2Tab2);

                //help overview of compound savings is shown when the help button is pressed
                btn1Tab2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Alert info=new Alert(Alert.AlertType.INFORMATION);
                        info.setContentText("This is a savings calculator with compounding interest with regular contributions.There are 5 fields and you can calculate any of the field by entering data for the other fields and keeping the relevant field empty!!!");
                        info.setHeaderText("ABOUT");
                        info.show();
                    }
                });

                //calculations of compound savings are done when button calculate is pressed
                btn2Tab2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //variables created for each parameter
                        double iDeposit;
                        double rate;
                        double time;
                        double mDeposit;
                        double fValue;
                        double output;
                        DecimalFormat df=new DecimalFormat("####.#");
                        Alert a=new Alert(Alert.AlertType.WARNING);
                        //checking whether the textfield for initial deposit is empty
                        //then finding the initial deposit based on other parameters
                        if(txt1Tab2.getText().isEmpty()) {
                            try {
                                rate = Double.parseDouble(txt2Tab2.getText());
                                time = Double.parseDouble(txt3Tab2.getText());
                                fValue = Double.parseDouble(txt5Tab2.getText());
                                mDeposit = Double.parseDouble(txt4Tab2.getText());
                                output =CompoundSavings.principalAmount(fValue,rate,time,mDeposit); //calling the method to calculate
                                txt1Tab2.setText("" + df.format(output));
                                History.compoundSavings(output,rate,time,mDeposit,fValue);  //calling the method to write data into the history file
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!");
                                a.show();
                            }

                            //checking whether the textfield for time is empty
                            //then finding the time based on other parameters
                        }else if(txt3Tab2.getText().isEmpty()){
                            try {
                                fValue = Double.parseDouble(txt5Tab2.getText());
                                rate = Double.parseDouble(txt2Tab2.getText());
                                mDeposit = Double.parseDouble(txt4Tab2.getText());
                                iDeposit = Double.parseDouble(txt1Tab2.getText());
                                output = CompoundSavings.time(iDeposit, fValue, rate, mDeposit); //calling the method to calculate
                                txt3Tab2.setText("" + df.format(output));
                                History.compoundSavings(iDeposit,rate,output,mDeposit,fValue); //calling the method to write data into the history file
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!");
                                a.show();
                            }

                            //checking whether the textfield for monthly deposit is empty
                            //then finding the monthly deposit based on other parameters
                        }else if(txt4Tab2.getText().isEmpty()){
                            try {
                                rate = Double.parseDouble(txt2Tab2.getText());
                                time = Double.parseDouble(txt3Tab2.getText());
                                fValue = Double.parseDouble(txt5Tab2.getText());
                                iDeposit = Double.parseDouble(txt1Tab2.getText());
                                output = CompoundSavings.monthlyDeposit(iDeposit, fValue, rate, time); //calling the method to calculate
                                txt4Tab2.setText("" + df.format(output));
                                History.compoundSavings(iDeposit,rate,time,output,fValue); //calling the method to write data into the history file
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!");
                                a.show();
                            }

                            //checking whether the textfield for future value is empty
                            //then finding the future value based on other parameters
                        }else if(txt5Tab2.getText().isEmpty()){
                            try {
                                rate = Double.parseDouble(txt2Tab2.getText());
                                time = Double.parseDouble(txt3Tab2.getText());
                                iDeposit = Double.parseDouble(txt1Tab2.getText());
                                mDeposit = Double.parseDouble(txt4Tab2.getText());
                                output = CompoundSavings.futureValue(iDeposit, rate, time, mDeposit); //calling the method to calculate
                                txt5Tab2.setText("" + df.format(output));
                                History.compoundSavings(iDeposit,rate,time,mDeposit,output); //calling the method to write data into the history file
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!");
                                a.show();
                            }

                        }
                    }
                });

                //tab3 is created for the loan calculator
                Tab tab3=new Tab("Loan");
                tab3.setId("tab3");
                AnchorPane p3=new AnchorPane();
                p3.setPrefWidth(200);
                p3.setPrefHeight(180);
                tab3.setContent(p3);

                //adding a background image for loan calculator
                Image img3=new Image("loan2.jpg");
                ImageView imgView3=new ImageView(img3);
                imgView3.setFitWidth(400);
                imgView3.setFitHeight(300);

                //creation of all the labels needed for the loan calculator
                Label lbl1Tab3=new Label("Loan Amount");
                lbl1Tab3.setLayoutX(37);
                lbl1Tab3.setLayoutY(29);
                Label lbl2Tab3=new Label("Interest Rate");
                lbl2Tab3.setLayoutX(37);
                lbl2Tab3.setLayoutY(64);
                Label lbl3Tab3=new Label("Loan Term");
                lbl3Tab3.setLayoutX(37);
                lbl3Tab3.setLayoutY(98);
                Label lbl4Tab3=new Label("Monthly Payment");
                lbl4Tab3.setLayoutX(37);
                lbl4Tab3.setLayoutY(139);
                Label lbl5Tab3=new Label("No Of Payments");
                lbl5Tab3.setLayoutX(37);
                lbl5Tab3.setLayoutY(177);

                //creation of all the textfields needed for the loan calculator
                TextField txt1Tab3=new TextField();
                txt1Tab3.setLayoutX(148);
                txt1Tab3.setLayoutY(25);
                txt1Tab3.setPromptText("$");
                TextField txt2Tab3=new TextField();
                txt2Tab3.setLayoutX(148);
                txt2Tab3.setLayoutY(60);
                txt2Tab3.setPromptText("%");
                TextField txt3Tab3=new TextField();
                txt3Tab3.setLayoutX(148);
                txt3Tab3.setLayoutY(94);
                txt3Tab3.setPromptText("years");
                TextField txt4Tab3=new TextField();
                txt4Tab3.setLayoutX(148);
                txt4Tab3.setLayoutY(135);
                txt4Tab3.setPromptText("$");
                TextField txt5Tab3=new TextField();
                txt5Tab3.setLayoutX(148);
                txt5Tab3.setLayoutY(173);

                //creating help button and calculate button for loan calculator
                Button btn1Tab3=new Button("Help");
                btn1Tab3.setLayoutX(61);
                btn1Tab3.setLayoutY(239);
                btn1Tab3.setId("help");
                Button btn2Tab3=new Button("Calculate");
                btn2Tab3.setLayoutX(223);
                btn2Tab3.setLayoutY(239);
                btn2Tab3.setId("calculate");

                //adding the elements to the anchor pane of tab3
                p3.getChildren().addAll(imgView3,lbl1Tab3,lbl2Tab3,lbl3Tab3,lbl4Tab3,lbl5Tab3,txt1Tab3,txt2Tab3,txt3Tab3,txt4Tab3,txt5Tab3,btn1Tab3,btn2Tab3);

                //help overview of loan is shown when the help button is pressed
                btn1Tab3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Alert info=new Alert(Alert.AlertType.INFORMATION);
                        info.setContentText("This is a loan calculator with compounding interest.There are 5 fields and you can calculate any of the field by entering data for the other fields and keeping the relevant field empty!!!\n No Of Payments is not a required field!!!");
                        info.setHeaderText("ABOUT");
                        info.show();
                    }
                });

                //calculations of loan are done when button calculate is pressed
                btn2Tab3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //variables created for each parameter
                        double lAmount;
                        double rate;
                        double time;
                        double mPayment;
                        int payments;
                        double output;
                        DecimalFormat df=new DecimalFormat("####.#");
                        Alert a=new Alert(Alert.AlertType.WARNING);
                        //checking whether the textfield for loan amount is empty
                        //then finding the loan amount based on other parameters
                        if(txt1Tab3.getText().isEmpty()) {
                            try {
                                rate = Double.parseDouble(txt2Tab3.getText());
                                time = Double.parseDouble(txt3Tab3.getText());
                                mPayment = Double.parseDouble(txt4Tab3.getText());
                                output = Loan.principalAmount(rate, time, mPayment); //calling the method to calculate
                                txt1Tab3.setText("" + df.format(output));
                                History.loan(output,rate,time,mPayment); //calling the method to write data into the history file
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!");
                                a.show();
                            }
                            //checking whether the textfield for time is empty
                            //then finding the time based on other parameters
                        }else if(txt3Tab3.getText().isEmpty()){
                            try {
                                mPayment = Double.parseDouble(txt4Tab3.getText());
                                rate = Double.parseDouble(txt2Tab3.getText());
                                lAmount = Double.parseDouble(txt1Tab3.getText());
                                output = Loan.time(lAmount, rate, mPayment); //calling the method to calculate
                                txt3Tab3.setText("" + df.format(output));
                                History.loan(lAmount,rate,output,mPayment); //calling the method to write data into the history file
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!");
                                a.show();
                            }
                            //checking whether the textfield for monthly payment is empty
                            //then finding the monthly payment based on other parameters
                        }else if(txt4Tab3.getText().isEmpty()){
                            try {
                                rate = Double.parseDouble(txt2Tab3.getText());
                                time = Double.parseDouble(txt3Tab3.getText());
                                lAmount = Double.parseDouble(txt1Tab3.getText());
                                output = Loan.monthlyPayment(lAmount, rate, time); //calling the method to calculate
                                txt4Tab3.setText("" + df.format(output));
                                History.loan(lAmount,rate,time,output); //calling the method to write data into the history file
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!");
                                a.show();
                            }
                            //checking whether the textfield for no of payments is empty
                            //then finding the no of payments based on other parameters
                        }else if(txt5Tab3.getText().isEmpty()){
                            try {
                                time = Double.parseDouble(txt3Tab3.getText());
                                output = Loan.noOfPayments(time); //calling the method to calculate
                                txt5Tab3.setText("" + df.format(output));
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!");
                                a.show();
                            }
                        }
                    }
                });




                //tab4 is created for the mortgage calculator
                Tab tab4=new Tab("Mortgage");
                tab4.setId("tab4");
                AnchorPane p4=new AnchorPane();
                p4.setPrefWidth(200);
                p4.setPrefHeight(180);
                tab4.setContent(p4);

                //adding a background image for mortgage calculator
                Image img4=new Image("mortgage2.jpg");
                ImageView imgView4=new ImageView(img4);
                imgView4.setFitWidth(400);
                imgView4.setFitHeight(300);

                //creation of all the labels needed for the mortgage calculator
                Label lbl1Tab4=new Label("Principal Amount");
                lbl1Tab4.setLayoutX(37);
                lbl1Tab4.setLayoutY(29);
                Label lbl2Tab4=new Label("Interest Rate");
                lbl2Tab4.setLayoutX(37);
                lbl2Tab4.setLayoutY(64);
                Label lbl3Tab4=new Label("Time");
                lbl3Tab4.setLayoutX(37);
                lbl3Tab4.setLayoutY(98);
                Label lbl4Tab4=new Label("Re-Payment");
                lbl4Tab4.setLayoutX(37);
                lbl4Tab4.setLayoutY(139);
                Label lbl5Tab4=new Label("No Of Payments");
                lbl5Tab4.setLayoutX(37);
                lbl5Tab4.setLayoutY(177);

                //creation of all the textfields needed for the mortgage calculator
                TextField txt1Tab4=new TextField();
                txt1Tab4.setLayoutX(148);
                txt1Tab4.setLayoutY(25);
                txt1Tab4.setPromptText("$");
                TextField txt2Tab4=new TextField();
                txt2Tab4.setLayoutX(148);
                txt2Tab4.setLayoutY(60);
                txt2Tab4.setPromptText("%");
                TextField txt3Tab4=new TextField();
                txt3Tab4.setLayoutX(148);
                txt3Tab4.setLayoutY(94);
                txt3Tab4.setPromptText("years");
                TextField txt4Tab4=new TextField();
                txt4Tab4.setLayoutX(148);
                txt4Tab4.setLayoutY(135);
                txt4Tab4.setPromptText("$");
                TextField txt5Tab4=new TextField();
                txt5Tab4.setLayoutX(148);
                txt5Tab4.setLayoutY(173);

                //creating help button and calculate button for mortgage calculator
                Button btn1Tab4=new Button("Help");
                btn1Tab4.setLayoutX(61);
                btn1Tab4.setLayoutY(239);
                btn1Tab4.setId("help");
                Button btn2Tab4=new Button("Calculate");
                btn2Tab4.setLayoutX(223);
                btn2Tab4.setLayoutY(239);
                btn2Tab4.setId("calculate");

                //adding the elements to the anchor pane of tab4
                p4.getChildren().addAll(imgView4,lbl1Tab4,lbl2Tab4,lbl3Tab4,lbl4Tab4,lbl5Tab4,txt1Tab4,txt2Tab4,txt3Tab4,txt4Tab4,txt5Tab4,btn1Tab4,btn2Tab4);

                //help overview of mortgage is shown when the help button is pressed
                btn1Tab4.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Alert info=new Alert(Alert.AlertType.INFORMATION);
                        info.setContentText("This is a mortgage calculator with compounding interest.There are 5 fields and you can calculate any of the field by entering data for the other fields and keeping the relevant field empty!!!\nNo Of Payments is not a required field!!!");
                        info.setHeaderText("ABOUT");
                        info.show();
                    }
                });

                //calculations of mortgage are done when button calculate is pressed
                btn2Tab4.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        double pAmount;
                        double rate;
                        double time;
                        double mPayment;
                        int payments;
                        double output;
                        DecimalFormat df=new DecimalFormat("####.#");
                        Alert a=new Alert(Alert.AlertType.WARNING);
                        //checking whether the textfield for principle amount is empty
                        //then finding the principle amount based on other parameters
                        if(txt1Tab4.getText().isEmpty()) {
                            try {
                                rate = Double.parseDouble(txt2Tab4.getText());
                                time = Double.parseDouble(txt3Tab4.getText());
                                mPayment = Double.parseDouble(txt4Tab4.getText());
                                output = Mortgage.principalAmount(rate, time, mPayment); //calling the method to calculate
                                txt1Tab4.setText("" + df.format(output));
                                History.mortgage(output,rate,time,mPayment); //calling the method to write data into the history file
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!");
                                a.show();
                            }
                            //checking whether the textfield for time is empty
                            //then finding the time based on other parameters
                        }else if(txt3Tab4.getText().isEmpty()){
                            try {
                                mPayment = Double.parseDouble(txt4Tab4.getText());
                                rate = Double.parseDouble(txt2Tab4.getText());
                                pAmount = Double.parseDouble(txt1Tab4.getText());
                                output = Mortgage.time(pAmount, rate, mPayment); //calling the method to calculate
                                txt3Tab4.setText("" + df.format(output));
                                History.mortgage(pAmount,rate,output,mPayment); //calling the method to write data into the history file
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!");
                                a.show();
                            }
                            //checking whether the textfield for repayment is empty
                            //then finding the repayment based on other parameters
                        }else if(txt4Tab4.getText().isEmpty()){
                            try {
                                rate = Double.parseDouble(txt2Tab4.getText());
                                time = Double.parseDouble(txt3Tab4.getText());
                                pAmount = Double.parseDouble(txt1Tab4.getText());
                                output = Mortgage.rePayment(pAmount, rate, time); //calling the method to calculate
                                txt4Tab4.setText("" + df.format(output));
                                History.mortgage(pAmount,rate,time,output); //calling the method to write data into the history file
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!");
                                a.show();
                            }
                            //checking whether the textfield for no of payments is empty
                            //then finding the no of payments based on other parameters
                        }else if(txt5Tab4.getText().isEmpty()){
                            try {
                                time = Double.parseDouble(txt3Tab4.getText());
                                output = Mortgage.noOfPayments(time); //calling the method to calculate
                                txt5Tab4.setText("" + df.format(output));
                            }catch (NumberFormatException e){
                                a.setContentText("Required fields are not filled or invalid input!!!");
                                a.show();
                            }
                        }
                    }
                });

                //tab5 is created for the history
                Tab tab5=new Tab("History");
                tab5.setId("tab5");
                AnchorPane p5=new AnchorPane();
                p5.setPrefSize(200,180);
                tab5.setContent(p5);

                //adding a background image for history
                Image img5=new Image("history.jpg");
                ImageView imgView5=new ImageView(img5);
                imgView5.setFitWidth(400);
                imgView5.setFitHeight(300);
                p5.getChildren().add(imgView5);

                //when user click on history tab, the calculate history gets added to the list view
                tab5.setOnSelectionChanged(new EventHandler<Event>() {
                    @Override
                    public void handle(Event event) {
                        ArrayList<String> outputList=new ArrayList<>();
                        outputList=History.historyRead(); //calling the method to read the data from history file
                        ListView<String> history=new ListView<>();
                        history.setPrefSize(256,239);
                        history.setLayoutX(29);
                        history.setLayoutY(30);
                        for(String i:outputList){
                            history.getItems().add(i); //each of the data in the history file will be added to the list view
                        }
                        p5.getChildren().add(history);
                    }
                });

                //adding all 5 tabs to the tabpane
                upSide.getTabs().addAll(tab1,tab2,tab3,tab4,tab5);


                //creating the keyboard/numpad which in the downside of the splitpane
                AnchorPane downSide=new AnchorPane();
                downSide.setId("keyboard");
                Label lblHead=new Label("VIRTUAL NUMPAD");
                ListView keyboard=new ListView<>();
                keyboard.setPrefSize(234,114);
                keyboard.setLayoutX(83);
                keyboard.setLayoutY(44);

                //creating the buttons needed for the numpad
                Button btnOne=new Button("1");
                btnOne.setPrefSize(44,37);
                btnOne.setLayoutX(83);
                btnOne.setLayoutY(43);

                Button btnTwo=new Button("2");
                btnTwo.setPrefSize(44,37);
                btnTwo.setLayoutX(127);
                btnTwo.setLayoutY(43);

                Button btnThree=new Button("3");
                btnThree.setPrefSize(44,37);
                btnThree.setLayoutX(171);
                btnThree.setLayoutY(43);

                Button btnFour=new Button("4");
                btnFour.setPrefSize(44,37);
                btnFour.setLayoutX(83);
                btnFour.setLayoutY(82);

                Button btnFive=new Button("5");
                btnFive.setPrefSize(44,37);
                btnFive.setLayoutX(127);
                btnFive.setLayoutY(82);

                Button btnSix=new Button("6");
                btnSix.setPrefSize(44,37);
                btnSix.setLayoutX(171);
                btnSix.setLayoutY(82);

                Button btnSeven=new Button("7");
                btnSeven.setPrefSize(44,37);
                btnSeven.setLayoutX(83);
                btnSeven.setLayoutY(120);

                Button btnEight=new Button("8");
                btnEight.setPrefSize(44,37);
                btnEight.setLayoutX(127);
                btnEight.setLayoutY(120);

                Button btnNine=new Button("9");
                btnNine.setPrefSize(44,37);
                btnNine.setLayoutX(171);
                btnNine.setLayoutY(120);

                Button btnZero=new Button("0");
                btnZero.setPrefSize(44,37);
                btnZero.setLayoutX(215);
                btnZero.setLayoutY(120);

                Button btnPoint=new Button(".");
                btnPoint.setPrefSize(44,37);
                btnPoint.setLayoutX(215);
                btnPoint.setLayoutY(82);

                Button btnMinus=new Button("-");
                btnMinus.setPrefSize(44,37);
                btnMinus.setLayoutX(215);
                btnMinus.setLayoutY(43);

                Button btnClear=new Button("C");
                btnClear.setPrefSize(58,37);
                btnClear.setLayoutX(259);
                btnClear.setLayoutY(43);

                Button btnBackSpace=new Button("X");
                btnBackSpace.setPrefSize(58,76);
                btnBackSpace.setLayoutX(259);
                btnBackSpace.setLayoutY(81);


                //adding all the buttons and other elements to the anchor pane of numpad
                downSide.getChildren().addAll(lblHead,keyboard,btnOne,btnTwo,btnThree,btnFour,btnFive,btnSix,btnSeven,btnEight,btnNine,btnZero,btnPoint,btnMinus,btnClear,btnBackSpace);

                //getting the last saved data from lastSaved file and filling them in relevant textfields of relevant tab
                try {
                        String[] inputList = new String[5];
                        inputList = LastSaved.lastSavedDataRead(); //calling the method to read data from the lastSaved file
                        String decider=inputList[0];
                        String input1 = inputList[1];
                        String input2 = inputList[2];
                        String input3 = inputList[3];
                        String input4 = inputList[4];
                        String input5 = inputList[5];

                        //checking whether which tab was selected last before it was closed and then filling the relevant textfields
                        if (decider.equals("sSavings")) {
                            txt1Tab1.setText(input1);
                            txt2Tab1.setText(input2);
                            txt3Tab1.setText(input3);
                            txt4Tab1.setText(input4);
                        } else if (decider.equals("cSavings")) {
                            txt1Tab2.setText(input1);
                            txt2Tab2.setText(input2);
                            txt3Tab2.setText(input3);
                            txt4Tab2.setText(input4);
                            txt5Tab2.setText(input5);
                        } else if (decider.equals("loan")) {
                            txt1Tab3.setText(input1);
                            txt2Tab3.setText(input2);
                            txt3Tab3.setText(input3);
                            txt4Tab3.setText(input4);
                            txt5Tab3.setText(input5);
                        } else if (decider.equals("mortgage")) {
                            txt1Tab4.setText(input1);
                            txt2Tab4.setText(input2);
                            txt3Tab4.setText(input3);
                            txt4Tab4.setText(input4);
                            txt5Tab4.setText(input5);
                        }

                }catch (NullPointerException e){
                    e.printStackTrace();
                }

                //adding the anchor panes of upside and downside to the splitpane
                sPane.getItems().add(upSide);
                sPane.getItems().add(downSide);

                //setting up the divider of the splitpane
                sPane.setOrientation(Orientation.VERTICAL);
                sPane.setDividerPosition(0,0.6506);

                //creating the scene of splitpane consisting everything created above
                calc.setScene(new Scene(sPane,400,500));
                sPane.getStylesheets().add(getClass().getResource("calculator.css").toExternalForm());
                calc.show();



                //creating mouseclick event for all the textfields of all the tabs to check which textfield is selected based on a certain value
                txt1Tab1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                         selectedTxtField=1;
                    }
                });

                txt2Tab1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=2;
                    }
                });

                txt3Tab1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=3;
                    }
                });

                txt4Tab1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=4;
                    }
                });

                txt1Tab2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=1;
                    }
                });

                txt2Tab2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=2;
                    }
                });

                txt3Tab2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=3;
                    }
                });

                txt4Tab2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=4;
                    }
                });

                txt5Tab2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=5;
                    }
                });

                txt1Tab3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=1;
                    }
                });

                txt2Tab3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=2;
                    }
                });

                txt3Tab3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=3;
                    }
                });
                txt4Tab3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=4;
                    }
                });

                txt5Tab3.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=5;
                    }
                });

                txt1Tab4.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=1;
                    }
                });

                txt2Tab4.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=2;
                    }
                });

                txt3Tab4.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=3;
                    }
                });

                txt4Tab4.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=4;
                    }
                });

                txt5Tab4.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        selectedTxtField=5;
                    }
                });


                //creating button on action events to fill the number that is clicked in the numpad to the selected textfield

                btnOne.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Tab selectedTab=upSide.getSelectionModel().getSelectedItem();
                        if(selectedTab.equals(tab1)){
                            if(selectedTxtField==1){
                                txt1Tab1.setText(txt1Tab1.getText()+btnOne.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab1.setText(txt2Tab1.getText()+btnOne.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab1.setText(txt3Tab1.getText()+btnOne.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab1.setText(txt4Tab1.getText()+btnOne.getText());
                            }
                        }else if(selectedTab.equals(tab2)){
                            if(selectedTxtField==1){
                                txt1Tab2.setText(txt1Tab2.getText()+btnOne.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab2.setText(txt2Tab2.getText()+btnOne.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab2.setText(txt3Tab2.getText()+btnOne.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab2.setText(txt4Tab2.getText()+btnOne.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab2.setText(txt5Tab2.getText()+btnOne.getText());
                            }
                        }else if(selectedTab.equals(tab3)){
                            if(selectedTxtField==1){
                                txt1Tab3.setText(txt1Tab3.getText()+btnOne.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab3.setText(txt2Tab3.getText()+btnOne.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab3.setText(txt3Tab3.getText()+btnOne.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab3.setText(txt4Tab3.getText()+btnOne.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab3.setText(txt5Tab3.getText()+btnOne.getText());
                            }
                        }else if(selectedTab.equals(tab4)){
                            if(selectedTxtField==1){
                                txt1Tab4.setText(txt1Tab4.getText()+btnOne.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab4.setText(txt2Tab4.getText()+btnOne.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab4.setText(txt3Tab4.getText()+btnOne.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab4.setText(txt4Tab4.getText()+btnOne.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab4.setText(txt5Tab4.getText()+btnOne.getText());
                            }
                        }
                    }
                });

                btnTwo.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Tab selectedTab=upSide.getSelectionModel().getSelectedItem();
                        if(selectedTab.equals(tab1)){
                            if(selectedTxtField==1){
                                txt1Tab1.setText(txt1Tab1.getText()+btnTwo.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab1.setText(txt2Tab1.getText()+btnTwo.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab1.setText(txt3Tab1.getText()+btnTwo.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab1.setText(txt4Tab1.getText()+btnTwo.getText());
                            }
                        }else if(selectedTab.equals(tab2)){
                            if(selectedTxtField==1){
                                txt1Tab2.setText(txt1Tab2.getText()+btnTwo.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab2.setText(txt2Tab2.getText()+btnTwo.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab2.setText(txt3Tab2.getText()+btnTwo.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab2.setText(txt4Tab2.getText()+btnTwo.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab2.setText(txt5Tab2.getText()+btnTwo.getText());
                            }
                        }else if(selectedTab.equals(tab3)){
                            if(selectedTxtField==1){
                                txt1Tab3.setText(txt1Tab3.getText()+btnTwo.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab3.setText(txt2Tab3.getText()+btnTwo.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab3.setText(txt3Tab3.getText()+btnTwo.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab3.setText(txt4Tab3.getText()+btnTwo.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab3.setText(txt5Tab3.getText()+btnTwo.getText());
                            }
                        }else if(selectedTab.equals(tab4)){
                            if(selectedTxtField==1){
                                txt1Tab4.setText(txt1Tab4.getText()+btnTwo.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab4.setText(txt2Tab4.getText()+btnTwo.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab4.setText(txt3Tab4.getText()+btnTwo.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab4.setText(txt4Tab4.getText()+btnTwo.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab4.setText(txt5Tab4.getText()+btnTwo.getText());
                            }
                        }
                    }
                });

                btnThree.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Tab selectedTab=upSide.getSelectionModel().getSelectedItem();
                        if(selectedTab.equals(tab1)){
                            if(selectedTxtField==1){
                                txt1Tab1.setText(txt1Tab1.getText()+btnThree.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab1.setText(txt2Tab1.getText()+btnThree.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab1.setText(txt3Tab1.getText()+btnThree.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab1.setText(txt4Tab1.getText()+btnThree.getText());
                            }
                        }else if(selectedTab.equals(tab2)){
                            if(selectedTxtField==1){
                                txt1Tab2.setText(txt1Tab2.getText()+btnThree.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab2.setText(txt2Tab2.getText()+btnThree.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab2.setText(txt3Tab2.getText()+btnThree.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab2.setText(txt4Tab2.getText()+btnThree.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab2.setText(txt5Tab2.getText()+btnThree.getText());
                            }
                        }else if(selectedTab.equals(tab3)){
                            if(selectedTxtField==1){
                                txt1Tab3.setText(txt1Tab3.getText()+btnThree.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab3.setText(txt2Tab3.getText()+btnThree.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab3.setText(txt3Tab3.getText()+btnThree.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab3.setText(txt4Tab3.getText()+btnThree.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab3.setText(txt5Tab3.getText()+btnThree.getText());
                            }
                        }else if(selectedTab.equals(tab4)){
                            if(selectedTxtField==1){
                                txt1Tab4.setText(txt1Tab4.getText()+btnThree.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab4.setText(txt2Tab4.getText()+btnThree.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab4.setText(txt3Tab4.getText()+btnThree.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab4.setText(txt4Tab4.getText()+btnThree.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab4.setText(txt5Tab4.getText()+btnThree.getText());
                            }
                        }
                    }
                });

                btnFour.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Tab selectedTab=upSide.getSelectionModel().getSelectedItem();
                        if(selectedTab.equals(tab1)){
                            if(selectedTxtField==1){
                                txt1Tab1.setText(txt1Tab1.getText()+btnFour.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab1.setText(txt2Tab1.getText()+btnFour.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab1.setText(txt3Tab1.getText()+btnFour.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab1.setText(txt4Tab1.getText()+btnFour.getText());
                            }
                        }else if(selectedTab.equals(tab2)){
                            if(selectedTxtField==1){
                                txt1Tab2.setText(txt1Tab2.getText()+btnFour.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab2.setText(txt2Tab2.getText()+btnFour.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab2.setText(txt3Tab2.getText()+btnFour.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab2.setText(txt4Tab2.getText()+btnFour.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab2.setText(txt5Tab2.getText()+btnFour.getText());
                            }
                        }else if(selectedTab.equals(tab3)){
                            if(selectedTxtField==1){
                                txt1Tab3.setText(txt1Tab3.getText()+btnFour.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab3.setText(txt2Tab3.getText()+btnFour.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab3.setText(txt3Tab3.getText()+btnFour.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab3.setText(txt4Tab3.getText()+btnFour.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab3.setText(txt5Tab3.getText()+btnFour.getText());
                            }
                        }else if(selectedTab.equals(tab4)){
                            if(selectedTxtField==1){
                                txt1Tab4.setText(txt1Tab4.getText()+btnFour.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab4.setText(txt2Tab4.getText()+btnFour.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab4.setText(txt3Tab4.getText()+btnFour.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab4.setText(txt4Tab4.getText()+btnFour.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab4.setText(txt5Tab4.getText()+btnFour.getText());
                            }
                        }
                    }
                });

                btnFive.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Tab selectedTab=upSide.getSelectionModel().getSelectedItem();
                        if(selectedTab.equals(tab1)){
                            if(selectedTxtField==1){
                                txt1Tab1.setText(txt1Tab1.getText()+btnFive.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab1.setText(txt2Tab1.getText()+btnFive.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab1.setText(txt3Tab1.getText()+btnFive.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab1.setText(txt4Tab1.getText()+btnFive.getText());
                            }
                        }else if(selectedTab.equals(tab2)){
                            if(selectedTxtField==1){
                                txt1Tab2.setText(txt1Tab2.getText()+btnFive.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab2.setText(txt2Tab2.getText()+btnFive.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab2.setText(txt3Tab2.getText()+btnFive.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab2.setText(txt4Tab2.getText()+btnFive.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab2.setText(txt5Tab2.getText()+btnFive.getText());
                            }
                        }else if(selectedTab.equals(tab3)){
                            if(selectedTxtField==1){
                                txt1Tab3.setText(txt1Tab3.getText()+btnFive.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab3.setText(txt2Tab3.getText()+btnFive.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab3.setText(txt3Tab3.getText()+btnFive.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab3.setText(txt4Tab3.getText()+btnFive.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab3.setText(txt5Tab3.getText()+btnFive.getText());
                            }
                        }else if(selectedTab.equals(tab4)){
                            if(selectedTxtField==1){
                                txt1Tab4.setText(txt1Tab4.getText()+btnFive.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab4.setText(txt2Tab4.getText()+btnFive.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab4.setText(txt3Tab4.getText()+btnFive.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab4.setText(txt4Tab4.getText()+btnFive.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab4.setText(txt5Tab4.getText()+btnFive.getText());
                            }
                        }
                    }
                });

                btnSix.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Tab selectedTab=upSide.getSelectionModel().getSelectedItem();
                        if(selectedTab.equals(tab1)){
                            if(selectedTxtField==1){
                                txt1Tab1.setText(txt1Tab1.getText()+btnSix.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab1.setText(txt2Tab1.getText()+btnSix.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab1.setText(txt3Tab1.getText()+btnSix.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab1.setText(txt4Tab1.getText()+btnSix.getText());
                            }
                        }else if(selectedTab.equals(tab2)){
                            if(selectedTxtField==1){
                                txt1Tab2.setText(txt1Tab2.getText()+btnSix.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab2.setText(txt2Tab2.getText()+btnSix.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab2.setText(txt3Tab2.getText()+btnSix.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab2.setText(txt4Tab2.getText()+btnSix.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab2.setText(txt5Tab2.getText()+btnSix.getText());
                            }
                        }else if(selectedTab.equals(tab3)){
                            if(selectedTxtField==1){
                                txt1Tab3.setText(txt1Tab3.getText()+btnSix.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab3.setText(txt2Tab3.getText()+btnSix.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab3.setText(txt3Tab3.getText()+btnSix.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab3.setText(txt4Tab3.getText()+btnSix.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab3.setText(txt5Tab3.getText()+btnSix.getText());
                            }
                        }else if(selectedTab.equals(tab4)){
                            if(selectedTxtField==1){
                                txt1Tab4.setText(txt1Tab4.getText()+btnSix.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab4.setText(txt2Tab4.getText()+btnSix.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab4.setText(txt3Tab4.getText()+btnSix.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab4.setText(txt4Tab4.getText()+btnSix.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab4.setText(txt5Tab4.getText()+btnSix.getText());
                            }
                        }
                    }
                });

                btnSeven.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Tab selectedTab=upSide.getSelectionModel().getSelectedItem();
                        if(selectedTab.equals(tab1)){
                            if(selectedTxtField==1){
                                txt1Tab1.setText(txt1Tab1.getText()+btnSeven.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab1.setText(txt2Tab1.getText()+btnSeven.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab1.setText(txt3Tab1.getText()+btnSeven.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab1.setText(txt4Tab1.getText()+btnSeven.getText());
                            }
                        }else if(selectedTab.equals(tab2)){
                            if(selectedTxtField==1){
                                txt1Tab2.setText(txt1Tab2.getText()+btnSeven.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab2.setText(txt2Tab2.getText()+btnSeven.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab2.setText(txt3Tab2.getText()+btnSeven.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab2.setText(txt4Tab2.getText()+btnSeven.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab2.setText(txt5Tab2.getText()+btnSeven.getText());
                            }
                        }else if(selectedTab.equals(tab3)){
                            if(selectedTxtField==1){
                                txt1Tab3.setText(txt1Tab3.getText()+btnSeven.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab3.setText(txt2Tab3.getText()+btnSeven.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab3.setText(txt3Tab3.getText()+btnSeven.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab3.setText(txt4Tab3.getText()+btnSeven.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab3.setText(txt5Tab3.getText()+btnSeven.getText());
                            }
                        }else if(selectedTab.equals(tab4)){
                            if(selectedTxtField==1){
                                txt1Tab4.setText(txt1Tab4.getText()+btnSeven.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab4.setText(txt2Tab4.getText()+btnSeven.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab4.setText(txt3Tab4.getText()+btnSeven.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab4.setText(txt4Tab4.getText()+btnSeven.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab4.setText(txt5Tab4.getText()+btnSeven.getText());
                            }
                        }
                    }
                });

                btnEight.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Tab selectedTab=upSide.getSelectionModel().getSelectedItem();
                        if(selectedTab.equals(tab1)){
                            if(selectedTxtField==1){
                                txt1Tab1.setText(txt1Tab1.getText()+btnEight.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab1.setText(txt2Tab1.getText()+btnEight.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab1.setText(txt3Tab1.getText()+btnEight.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab1.setText(txt4Tab1.getText()+btnEight.getText());
                            }
                        }else if(selectedTab.equals(tab2)){
                            if(selectedTxtField==1){
                                txt1Tab2.setText(txt1Tab2.getText()+btnEight.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab2.setText(txt2Tab2.getText()+btnEight.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab2.setText(txt3Tab2.getText()+btnEight.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab2.setText(txt4Tab2.getText()+btnEight.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab2.setText(txt5Tab2.getText()+btnEight.getText());
                            }
                        }else if(selectedTab.equals(tab3)){
                            if(selectedTxtField==1){
                                txt1Tab3.setText(txt1Tab3.getText()+btnEight.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab3.setText(txt2Tab3.getText()+btnEight.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab3.setText(txt3Tab3.getText()+btnEight.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab3.setText(txt4Tab3.getText()+btnEight.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab3.setText(txt5Tab3.getText()+btnEight.getText());
                            }
                        }else if(selectedTab.equals(tab4)){
                            if(selectedTxtField==1){
                                txt1Tab4.setText(txt1Tab4.getText()+btnEight.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab4.setText(txt2Tab4.getText()+btnEight.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab4.setText(txt3Tab4.getText()+btnEight.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab4.setText(txt4Tab4.getText()+btnEight.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab4.setText(txt5Tab4.getText()+btnEight.getText());
                            }
                        }
                    }
                });

                btnNine.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Tab selectedTab=upSide.getSelectionModel().getSelectedItem();
                        if(selectedTab.equals(tab1)){
                            if(selectedTxtField==1){
                                txt1Tab1.setText(txt1Tab1.getText()+btnNine.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab1.setText(txt2Tab1.getText()+btnNine.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab1.setText(txt3Tab1.getText()+btnNine.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab1.setText(txt4Tab1.getText()+btnNine.getText());
                            }
                        }else if(selectedTab.equals(tab2)){
                            if(selectedTxtField==1){
                                txt1Tab2.setText(txt1Tab2.getText()+btnNine.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab2.setText(txt2Tab2.getText()+btnNine.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab2.setText(txt3Tab2.getText()+btnNine.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab2.setText(txt4Tab2.getText()+btnNine.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab2.setText(txt5Tab2.getText()+btnNine.getText());
                            }
                        }else if(selectedTab.equals(tab3)){
                            if(selectedTxtField==1){
                                txt1Tab3.setText(txt1Tab3.getText()+btnNine.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab3.setText(txt2Tab3.getText()+btnNine.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab3.setText(txt3Tab3.getText()+btnNine.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab3.setText(txt4Tab3.getText()+btnNine.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab3.setText(txt5Tab3.getText()+btnNine.getText());
                            }
                        }else if(selectedTab.equals(tab4)){
                            if(selectedTxtField==1){
                                txt1Tab4.setText(txt1Tab4.getText()+btnNine.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab4.setText(txt2Tab4.getText()+btnNine.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab4.setText(txt3Tab4.getText()+btnNine.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab4.setText(txt4Tab4.getText()+btnNine.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab4.setText(txt5Tab4.getText()+btnNine.getText());
                            }
                        }
                    }
                });

                btnZero.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Tab selectedTab=upSide.getSelectionModel().getSelectedItem();
                        if(selectedTab.equals(tab1)){
                            if(selectedTxtField==1){
                                txt1Tab1.setText(txt1Tab1.getText()+btnZero.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab1.setText(txt2Tab1.getText()+btnZero.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab1.setText(txt3Tab1.getText()+btnZero.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab1.setText(txt4Tab1.getText()+btnZero.getText());
                            }
                        }else if(selectedTab.equals(tab2)){
                            if(selectedTxtField==1){
                                txt1Tab2.setText(txt1Tab2.getText()+btnZero.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab2.setText(txt2Tab2.getText()+btnZero.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab2.setText(txt3Tab2.getText()+btnZero.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab2.setText(txt4Tab2.getText()+btnZero.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab2.setText(txt5Tab2.getText()+btnZero.getText());
                            }
                        }else if(selectedTab.equals(tab3)){
                            if(selectedTxtField==1){
                                txt1Tab3.setText(txt1Tab3.getText()+btnZero.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab3.setText(txt2Tab3.getText()+btnZero.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab3.setText(txt3Tab3.getText()+btnZero.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab3.setText(txt4Tab3.getText()+btnZero.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab3.setText(txt5Tab3.getText()+btnZero.getText());
                            }
                        }else if(selectedTab.equals(tab4)){
                            if(selectedTxtField==1){
                                txt1Tab4.setText(txt1Tab4.getText()+btnZero.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab4.setText(txt2Tab4.getText()+btnZero.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab4.setText(txt3Tab4.getText()+btnZero.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab4.setText(txt4Tab4.getText()+btnZero.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab4.setText(txt5Tab4.getText()+btnZero.getText());
                            }
                        }
                    }
                });

                btnPoint.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Tab selectedTab=upSide.getSelectionModel().getSelectedItem();
                        if(selectedTab.equals(tab1)){
                            if(selectedTxtField==1){
                                txt1Tab1.setText(txt1Tab1.getText()+btnPoint.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab1.setText(txt2Tab1.getText()+btnPoint.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab1.setText(txt3Tab1.getText()+btnPoint.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab1.setText(txt4Tab1.getText()+btnPoint.getText());
                            }
                        }else if(selectedTab.equals(tab2)){
                            if(selectedTxtField==1){
                                txt1Tab2.setText(txt1Tab2.getText()+btnPoint.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab2.setText(txt2Tab2.getText()+btnPoint.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab2.setText(txt3Tab2.getText()+btnPoint.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab2.setText(txt4Tab2.getText()+btnPoint.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab2.setText(txt5Tab2.getText()+btnPoint.getText());
                            }
                        }else if(selectedTab.equals(tab3)){
                            if(selectedTxtField==1){
                                txt1Tab3.setText(txt1Tab3.getText()+btnPoint.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab3.setText(txt2Tab3.getText()+btnPoint.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab3.setText(txt3Tab3.getText()+btnPoint.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab3.setText(txt4Tab3.getText()+btnPoint.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab3.setText(txt5Tab3.getText()+btnPoint.getText());
                            }
                        }else if(selectedTab.equals(tab4)){
                            if(selectedTxtField==1){
                                txt1Tab4.setText(txt1Tab4.getText()+btnPoint.getText());
                            }else if(selectedTxtField==2){
                                txt2Tab4.setText(txt2Tab4.getText()+btnPoint.getText());
                            }else if(selectedTxtField==3){
                                txt3Tab4.setText(txt3Tab4.getText()+btnPoint.getText());
                            }else if(selectedTxtField==4){
                                txt4Tab4.setText(txt4Tab4.getText()+btnPoint.getText());
                            }else if(selectedTxtField==5){
                                txt5Tab4.setText(txt5Tab4.getText()+btnPoint.getText());
                            }
                        }
                    }
                });

                btnMinus.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Tab selectedTab=upSide.getSelectionModel().getSelectedItem();
                        if(selectedTab.equals(tab1)){
                            if(selectedTxtField==1 && txt1Tab1.getText().isEmpty()){
                                txt1Tab1.setText(btnMinus.getText());
                            }else if(selectedTxtField==2 && txt2Tab1.getText().isEmpty()){
                                txt2Tab1.setText(btnMinus.getText());
                            }else if(selectedTxtField==3 && txt3Tab1.getText().isEmpty()){
                                txt3Tab1.setText(btnMinus.getText());
                            }else if(selectedTxtField==4 && txt4Tab1.getText().isEmpty()){
                                txt4Tab1.setText(btnMinus.getText());
                            }
                        }else if(selectedTab.equals(tab2)){
                            if(selectedTxtField==1 && txt1Tab2.getText().isEmpty()){
                                txt1Tab2.setText(btnMinus.getText());
                            }else if(selectedTxtField==2 && txt2Tab2.getText().isEmpty()){
                                txt2Tab2.setText(btnMinus.getText());
                            }else if(selectedTxtField==3 && txt3Tab2.getText().isEmpty()){
                                txt3Tab2.setText(btnMinus.getText());
                            }else if(selectedTxtField==4 && txt4Tab2.getText().isEmpty()){
                                txt4Tab2.setText(btnMinus.getText());
                            }else if(selectedTxtField==5 && txt5Tab2.getText().isEmpty()){
                                txt5Tab2.setText(btnMinus.getText());
                            }
                        }else if(selectedTab.equals(tab3)){
                            if(selectedTxtField==1 && txt1Tab3.getText().isEmpty()){
                                txt1Tab3.setText(btnMinus.getText());
                            }else if(selectedTxtField==2 && txt2Tab3.getText().isEmpty()){
                                txt2Tab3.setText(btnMinus.getText());
                            }else if(selectedTxtField==3 && txt3Tab3.getText().isEmpty()){
                                txt3Tab3.setText(btnMinus.getText());
                            }else if(selectedTxtField==4 && txt4Tab3.getText().isEmpty()){
                                txt4Tab3.setText(btnMinus.getText());
                            }else if(selectedTxtField==5 && txt5Tab3.getText().isEmpty()){
                                txt5Tab3.setText(btnMinus.getText());
                            }
                        }else if(selectedTab.equals(tab4)){
                            if(selectedTxtField==1 && txt1Tab4.getText().isEmpty()){
                                txt1Tab4.setText(btnMinus.getText());
                            }else if(selectedTxtField==2 && txt2Tab4.getText().isEmpty()){
                                txt2Tab4.setText(btnMinus.getText());
                            }else if(selectedTxtField==3 && txt3Tab4.getText().isEmpty()){
                                txt3Tab4.setText(btnMinus.getText());
                            }else if(selectedTxtField==4 && txt4Tab4.getText().isEmpty()){
                                txt4Tab4.setText(btnMinus.getText());
                            }else if(selectedTxtField==5 && txt5Tab4.getText().isEmpty()){
                                txt5Tab4.setText(btnMinus.getText());

                            }
                        }
                    }
                });

                btnClear.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Tab selectedTab=upSide.getSelectionModel().getSelectedItem();
                        if(selectedTab.equals(tab1)){
                            if(selectedTxtField==1){
                                txt1Tab1.setText("");
                            }else if(selectedTxtField==2){
                                txt2Tab1.setText("");
                            }else if(selectedTxtField==3){
                                txt3Tab1.setText("");
                            }else if(selectedTxtField==4){
                                txt4Tab1.setText("");
                            }
                        }else if(selectedTab.equals(tab2)){
                            if(selectedTxtField==1){
                                txt1Tab2.setText("");
                            }else if(selectedTxtField==2){
                                txt2Tab2.setText("");
                            }else if(selectedTxtField==3){
                                txt3Tab2.setText("");
                            }else if(selectedTxtField==4){
                                txt4Tab2.setText("");
                            }else if(selectedTxtField==5){
                                txt5Tab2.setText("");
                            }
                        }else if(selectedTab.equals(tab3)){
                            if(selectedTxtField==1){
                                txt1Tab3.setText("");
                            }else if(selectedTxtField==2){
                                txt2Tab3.setText("");
                            }else if(selectedTxtField==3){
                                txt3Tab3.setText("");
                            }else if(selectedTxtField==4){
                                txt4Tab3.setText("");
                            }else if(selectedTxtField==5){
                                txt5Tab3.setText("");
                            }
                        }else if(selectedTab.equals(tab4)){
                            if(selectedTxtField==1){
                                txt1Tab4.setText("");
                            }else if(selectedTxtField==2){
                                txt2Tab4.setText("");
                            }else if(selectedTxtField==3){
                                txt3Tab4.setText("");
                            }else if(selectedTxtField==4){
                                txt4Tab4.setText("");
                            }else if(selectedTxtField==5){
                                txt5Tab4.setText("");
                            }
                        }
                    }
                });

                btnBackSpace.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Tab selectedTab=upSide.getSelectionModel().getSelectedItem();
                        StringBuilder selectedString=new StringBuilder();
                        if(selectedTab.equals(tab1)){
                            if(selectedTxtField==1){
                                selectedString= new StringBuilder(txt1Tab1.getText());
                                selectedString.deleteCharAt(txt1Tab1.getText().length()-1);
                                txt1Tab1.setText(selectedString.toString());
                            }else if(selectedTxtField==2){
                                selectedString= new StringBuilder(txt2Tab1.getText());
                                selectedString.deleteCharAt(txt2Tab1.getText().length()-1);
                                txt2Tab1.setText(selectedString.toString());
                            }else if(selectedTxtField==3){
                                selectedString= new StringBuilder(txt3Tab1.getText());
                                selectedString.deleteCharAt(txt3Tab1.getText().length()-1);
                                txt3Tab1.setText(selectedString.toString());
                            }else if(selectedTxtField==4){
                                selectedString= new StringBuilder(txt4Tab1.getText());
                                selectedString.deleteCharAt(txt4Tab1.getText().length()-1);
                                txt4Tab1.setText(selectedString.toString());
                            }
                        }else if(selectedTab.equals(tab2)){
                            if(selectedTxtField==1){
                                selectedString= new StringBuilder(txt1Tab2.getText());
                                selectedString.deleteCharAt(txt1Tab2.getText().length()-1);
                                txt1Tab2.setText(selectedString.toString());
                            }else if(selectedTxtField==2){
                                selectedString= new StringBuilder(txt2Tab2.getText());
                                selectedString.deleteCharAt(txt2Tab2.getText().length()-1);
                                txt2Tab2.setText(selectedString.toString());
                            }else if(selectedTxtField==3){
                                selectedString= new StringBuilder(txt3Tab2.getText());
                                selectedString.deleteCharAt(txt3Tab2.getText().length()-1);
                                txt3Tab2.setText(selectedString.toString());
                            }else if(selectedTxtField==4){
                                selectedString= new StringBuilder(txt4Tab2.getText());
                                selectedString.deleteCharAt(txt4Tab2.getText().length()-1);
                                txt4Tab2.setText(selectedString.toString());
                            }else if(selectedTxtField==5){
                                selectedString= new StringBuilder(txt5Tab2.getText());
                                selectedString.deleteCharAt(txt5Tab2.getText().length()-1);
                                txt5Tab2.setText(selectedString.toString());
                            }
                        }else if(selectedTab.equals(tab3)){
                            if(selectedTxtField==1){
                                selectedString= new StringBuilder(txt1Tab3.getText());
                                selectedString.deleteCharAt(txt1Tab3.getText().length()-1);
                                txt1Tab3.setText(selectedString.toString());
                            }else if(selectedTxtField==2){
                                selectedString= new StringBuilder(txt2Tab3.getText());
                                selectedString.deleteCharAt(txt2Tab3.getText().length()-1);
                                txt2Tab3.setText(selectedString.toString());
                            }else if(selectedTxtField==3){
                                selectedString= new StringBuilder(txt3Tab3.getText());
                                selectedString.deleteCharAt(txt3Tab3.getText().length()-1);
                                txt3Tab3.setText(selectedString.toString());
                            }else if(selectedTxtField==4){
                                selectedString= new StringBuilder(txt4Tab3.getText());
                                selectedString.deleteCharAt(txt4Tab3.getText().length()-1);
                                txt4Tab3.setText(selectedString.toString());
                            }else if(selectedTxtField==5){
                                selectedString= new StringBuilder(txt5Tab3.getText());
                                selectedString.deleteCharAt(txt5Tab3.getText().length()-1);
                                txt5Tab3.setText(selectedString.toString());
                            }
                        }else if(selectedTab.equals(tab4)){
                            if(selectedTxtField==1){
                                selectedString= new StringBuilder(txt1Tab4.getText());
                                selectedString.deleteCharAt(txt1Tab4.getText().length()-1);
                                txt1Tab4.setText(selectedString.toString());
                            }else if(selectedTxtField==2){
                                selectedString= new StringBuilder(txt2Tab4.getText());
                                selectedString.deleteCharAt(txt2Tab4.getText().length()-1);
                                txt2Tab4.setText(selectedString.toString());
                            }else if(selectedTxtField==3){
                                selectedString= new StringBuilder(txt3Tab4.getText());
                                selectedString.deleteCharAt(txt3Tab4.getText().length()-1);
                                txt3Tab4.setText(selectedString.toString());
                            }else if(selectedTxtField==4){
                                selectedString= new StringBuilder(txt4Tab4.getText());
                                selectedString.deleteCharAt(txt4Tab4.getText().length()-1);
                                txt4Tab4.setText(selectedString.toString());
                            }else if(selectedTxtField==5){
                                selectedString= new StringBuilder(txt5Tab4.getText());
                                selectedString.deleteCharAt(txt5Tab4.getText().length()-1);
                                txt5Tab4.setText(selectedString.toString());
                            }
                        }
                    }
                });

                //when the window gets closed, getting the last entered data from the last selected tab and writing them to the LastSaved file
                calc.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        String input0="";
                        String input1="";
                        String input2="";
                        String input3="";
                        String input4="";
                        String input5="";
                        Tab lastSelectedTab=upSide.getSelectionModel().getSelectedItem();
                        if(lastSelectedTab.equals(tab1)){
                            input0="sSavings";
                            input1=txt1Tab1.getText();
                            input2=txt2Tab1.getText();
                            input3=txt3Tab1.getText();
                            input4=txt4Tab1.getText();
                            LastSaved.lastSavedDataWrite(input0,input1,input2,input3,input4,input5);
                        }else if(lastSelectedTab.equals(tab2)){
                            input0="cSavings";
                            input1=txt1Tab2.getText();
                            input2=txt2Tab2.getText();
                            input3=txt3Tab2.getText();
                            input4=txt4Tab2.getText();
                            input5=txt5Tab2.getText();
                            LastSaved.lastSavedDataWrite(input0,input1,input2,input3,input4,input5);
                        }else if(lastSelectedTab.equals(tab3)){
                            input0="loan";
                            input1=txt1Tab3.getText();
                            input2=txt2Tab3.getText();
                            input3=txt3Tab3.getText();
                            input4=txt4Tab3.getText();
                            input5=txt5Tab3.getText();
                            LastSaved.lastSavedDataWrite(input0,input1,input2,input3,input4,input5);
                        }else if(lastSelectedTab.equals(tab4)){
                            input0="mortgage";
                            input1=txt1Tab4.getText();
                            input2=txt2Tab4.getText();
                            input3=txt3Tab4.getText();
                            input4=txt4Tab4.getText();
                            input5=txt5Tab4.getText();
                            LastSaved.lastSavedDataWrite(input0,input1,input2,input3,input4,input5);
                        }
                    }
                });

            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage home=(Stage) btnExit.getScene().getWindow();
                home.close();
            }
        });

        

    }



    public static void main(String[] args) {
        launch(args);
    }
}
