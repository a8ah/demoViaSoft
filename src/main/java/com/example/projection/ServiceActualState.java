package com.example.projection;

import com.example.entity.Status;

public class ServiceActualState {

    private String service;

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    private Status status;

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    

    

    public ServiceActualState(String service, Status status) {
        this.service = service;
        this.status = status;
    }


}
