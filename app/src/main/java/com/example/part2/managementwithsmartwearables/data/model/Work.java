package com.example.part2.managementwithsmartwearables.data.model;

public class Work {
    int idx;
    User worker;
    String workDetail;
    int approve;
    int workStatus;

    public Work() {
    }

    public Work(int idx, User worker, String workDetail, int approve, int workStatus) {
        this.idx = idx;
        this.worker = worker;
        this.workDetail = workDetail;
        this.approve = approve;
        this.workStatus = workStatus;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public User getWorker() {
        return worker;
    }

    public void setWorker(User worker) {
        this.worker = worker;
    }

    public String getWorkDetail() {
        return workDetail;
    }

    public void setWorkDetail(String workDetail) {
        this.workDetail = workDetail;
    }

    public int getApprove() {
        return approve;
    }

    public void setApprove(int approve) {
        this.approve = approve;
    }

    public int getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(int workStatus) {
        this.workStatus = workStatus;
    }

    @Override
    public String toString() {
        return "Work{" +
                "idx=" + idx +
                ", worker=" + worker +
                ", workDetail='" + workDetail + '\'' +
                ", approve=" + approve +
                ", workStatus=" + workStatus +
                '}';
    }
}
