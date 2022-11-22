package com.example.cursova2.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.cursova2.command.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import com.example.cursova2.menu.TrainService;
import com.example.cursova2.object.Car;
import com.example.cursova2.sql.DatabsaseHandler;

public class MainController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private MenuItem createNewCar;
    @FXML
    private MenuItem createNewTrain;

    @FXML
    private MenuItem delCar;

    @FXML
    private MenuItem delTrain;

    @FXML
    private VBox main;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuCRND;

    @FXML
    private Menu menuShow;


    @FXML
    private MenuItem showCarWithNumber;

    @FXML
    private MenuItem showTrainWithCars;

    @FXML
    private MenuItem showTrainWithNumber;
    @FXML
    private Button searchButton;
    @FXML
    private TextField trainIDFild;
    @FXML
    private TextField freePlacesFild;
    @FXML
    private Label warningsLabel;

    private static TrainService service = new TrainService();
    private int trainID=-1;
    private int freePlacesNumber=-1;
    public static Receiver addAllCommand(){
        Receiver receiver = new Receiver();
        receiver.addCommand(new CreateTrainComand(service));
        receiver.addCommand(new CreateCarCommand(service));
        receiver.addCommand(new DeleteTrain(service));
        receiver.addCommand(new DeleteCar(service));
        receiver.addCommand(new ShowCarWithNumber(service));
        receiver.addCommand(new ShowTrainWithNumber(service));
        receiver.addCommand(new ShowTrainWithParameters(service));
        return receiver;
    }



    public void initialize() {
        Receiver receiver = addAllCommand();
   /*     DatabsaseHandler databsaseHandler = new DatabsaseHandler();
        databsaseHandler.fillTrainsHashMapData(TrainService.trains);*/

        searchButton.setOnAction(e->{
            setTrainID();
            setFreePlacesFild();
            List<Car> necessaryCars = getNecessaryCars();
            if(!necessaryCars.isEmpty()){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succeed");
                String carsSequences="";
                for(Car car: necessaryCars)
                    carsSequences=carsSequences+car.getSequenceNumber();
                alert.setContentText("The appropriate cars' from train "+trainID+":\n"+carsSequences);
                alert.showAndWait();
            }else warningsLabel.setText(warningsLabel.getText()+"There is not cars with such number of free place in train!");
        });

        createNewTrain.setOnAction(e-> {
            receiver.runCommand(0);
        });
        createNewCar.setOnAction(e->{
            receiver.runCommand(1);
        });
        delTrain.setOnAction(e->{
            receiver.runCommand(2);
        });
        delCar.setOnAction(e->{
            receiver.runCommand(3);
        });
        showCarWithNumber.setOnAction(e->{
            receiver.runCommand(4);
        });
        showTrainWithNumber.setOnAction(e->{
            receiver.runCommand(5);
        });
        showTrainWithCars.setOnAction(e->{
            receiver.runCommand(6);
        });


    }

    private void setTrainID(){
        try {
            if(checkIDGreterThenZero(Integer.parseInt(trainIDFild.getText())))
                trainID=Integer.parseInt(trainIDFild.getText());
        }  catch (NumberFormatException e){
            warningsLabel.setText(warningsLabel.getText()+"\n"+"Train ID must be a number");
        }catch (Exception e){
            warningsLabel.setText(warningsLabel.getText()+"\n"+e);
        }
    }

    private void setFreePlacesFild(){
        try {
            if(checkIDGreterThenZero(Integer.parseInt(freePlacesFild.getText())))
                freePlacesNumber=Integer.parseInt(freePlacesFild.getText());
        }  catch (NumberFormatException e){
            warningsLabel.setText(warningsLabel.getText()+"\n"+"Number of free places must be a number");
        }catch (Exception e){
            warningsLabel.setText(warningsLabel.getText()+"\n"+e);
        }
    }

    public boolean checkIDGreterThenZero(int number){
        if(number<0) {
            warningsLabel.setText(warningsLabel.getText()+"\n"+"All numbers must be greater then zero");
            return false;
        }
        return true;
    }

    private List<Car> getNecessaryCars(){
        List<Car> cars = new ArrayList<>();
        for(Car car: TrainService.trains.get(trainID).getCars()){
            if(car.getFreePlaces()==freePlacesNumber)
                cars.add(car);
        }
        return cars;
    }



}