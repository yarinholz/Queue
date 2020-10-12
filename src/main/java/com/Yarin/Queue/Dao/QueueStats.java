package com.Yarin.Queue.Dao;

public class QueueStats {
    private static QueueStats instance = null;
    private long size;
    private int nextId=0;

    private QueueStats(){
    }

    public static QueueStats getInstance(){
        if(instance==null){
            instance = new QueueStats();
        }
        return instance;
    }
    public void setNextId(int nextId){
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
