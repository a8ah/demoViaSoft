package com.example.projection;

import com.example.entity.Status;

public class ServiceActualState {

    private String state;

    private Status status;

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ServiceActualState(String state, Status status) {
        this.state = state;
        this.status = status;
    }


}
