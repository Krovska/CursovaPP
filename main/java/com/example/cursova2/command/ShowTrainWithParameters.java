package com.example.cursova2.command;

import com.example.cursova2.menu.TrainService ;

public class ShowTrainWithParameters implements Command{

    TrainService service = new TrainService();

    public ShowTrainWithParameters(TrainService service){
        this.service = service;
    }

    @Override
    public void executeMenu(TrainService service) {
        service.showTrainWythSortedCarsByComfort();

    }
}
