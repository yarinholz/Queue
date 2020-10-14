package com.Yarin.Queue.Dao;

public class QueueState {
    private static QueueState instance = null;
    private long size;
    private int nextId=0;

    private QueueState(){
    }

    public static QueueState getInstance(){
        if(instance==null){
            instance = new QueueState();
        }
        return instance;
    }
    public void setNextId(int nextId) {
        this.nextId=nextId;
    }


    public int getNextId() {
        return nextId;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void increaseId(){
        this.nextId++;
    }

    public void increaseSize(){
        this.size++;
    }

    public void decreaseSize(){
        size--;
    }

}
