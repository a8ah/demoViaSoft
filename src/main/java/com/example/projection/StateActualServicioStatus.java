package com.example.projection;

import java.util.List;

public class StateActualServicioStatus {

    private String state;

    private List<ServiceActualState> serviceState;

    public List<ServiceActualState> getServiceState() {
        return this.serviceState;
    }

    public void setServiceState(List<ServiceActualState> serviceState) {
        this.serviceState = serviceState;
    }    

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public StateActualServicioStatus(String state, List<ServiceActualState> serviceState) {
        this.state = state;
        this.serviceState = serviceState;
    }


}
