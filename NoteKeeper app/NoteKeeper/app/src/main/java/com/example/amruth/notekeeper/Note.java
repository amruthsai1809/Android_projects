package com.example.amruth.notekeeper;

/**
 * Created by amruth on 27/02/17.
 */

public class Note {
    private long _id;
    private String note;
    private String priority;
    private int status;
    private int prioritycode;
    private long updatetime;

    public Note(String note, String priority, int status, int prioritycode, long updatetime) {
        this.note = note;
        this.priority = priority;
        this.status = status;
        this.prioritycode = prioritycode;
        this.updatetime = updatetime;
    }
    public Note(){

    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrioritycode() {
        return prioritycode;
    }

    public void setPrioritycode(int prioritycode) {
        this.prioritycode = prioritycode;
    }

    public long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(long updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "Note{" +
                "_id=" + _id +
                ", note='" + note + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", prioritycode=" + prioritycode +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }
}
