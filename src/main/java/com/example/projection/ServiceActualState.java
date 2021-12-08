package com.example.projection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.entity.Status;

public class ServiceActualState {

    private String service;

    private Status status;

    private String createdDate;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a");

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }
    

    public ServiceActualState(String service, Status status) {
        this.service = service;
        this.status = status;
    }

    public ServiceActualState(String service, Status status, LocalDateTime creado) {
        this.service = service;
        this.status = status;
        this.createdDate = dateTimeFormatter.format(creado);

        
    }

    
}
