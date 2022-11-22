package com.example.cursova2.command;

import com.example.cursova2.menu.TrainService ;

public class ShowCarWithNumber implements Command{

    TrainService service = new TrainService();

    public ShowCarWithNumber(TrainService service){
        this.service = service;
    }

    @Override
    public void executeMenu(TrainService service) {
        service.showCarWithNumber();
    }
}
