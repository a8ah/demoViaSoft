package com.example.projection;

import javax.persistence.Column;

import com.example.entity.State;

import java.util.UUID;

public class MoreAffectedState {

    private String state;

    private long affectedTimes;

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getAffectedTimes() {
        return this.affectedTimes;
    }

    public void setAffectedTimes(long affectedTimes) {
        this.affectedTimes = affectedTimes;
    }

    public MoreAffectedState(String state, long affectedTimes) {
        this.state = state;
        this.affectedTimes = affectedTimes;
    }


}
