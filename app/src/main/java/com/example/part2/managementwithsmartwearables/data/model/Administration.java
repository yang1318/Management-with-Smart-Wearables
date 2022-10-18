package com.example.part2.managementwithsmartwearables.data.model;

public class Administration {
    Worker worker;
    Work work;

    public Administration() { }

    public Administration(Worker worker, Work work) {
        this.worker = worker;
        this.work = work;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

}
