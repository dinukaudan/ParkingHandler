package com.example.tharindu_prasad.parkinghandler.data;

/**
 * Created by nipunu on 1/4/2018.
 */

public class SlotObject {
    private int id;
    private boolean availability;
    private int time_begin;
    private int time_dura;

    public SlotObject(int id,boolean availability){
        this.id = id;
        this.availability = availability;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime_begin() {
        return time_begin;
    }

    public void setTime_begin(int time_begin) {
        this.time_begin = time_begin;
    }

    public int getTime_dura() {
        return time_dura;
    }

    public void setTime_dura(int time_dura) {
        this.time_dura = time_dura;
    }
}
