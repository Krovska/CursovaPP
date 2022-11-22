package com.example.cursova2.command;

import com.example.cursova2.menu.TrainService;

public class DeleteCar implements Command{
    TrainService service;

    public DeleteCar(TrainService service){
        this.service = service;
    }

    @Override
    public void executeMenu(TrainService service) {
        service.deleteCar();
    }
}