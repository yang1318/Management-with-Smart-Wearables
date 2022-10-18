package com.example.part2.managementwithsmartwearables.data.model;

public class Work {
    int id;
    String workName;
    int status;

    public Work() {
    }

    public Work(int id, String workName, int status) {
        this.id = id;
        this.workName = workName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
