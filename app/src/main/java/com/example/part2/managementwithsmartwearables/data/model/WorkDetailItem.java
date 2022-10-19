package com.example.part2.managementwithsmartwearables.data.model;

public class WorkDetailItem extends Work{
    Boolean expended;

    public WorkDetailItem() {
    }

    public WorkDetailItem(int idx, User worker, String workDetail, int approve, int workStatus) {
        this.expended = false;
        this.idx = idx;
        this.worker = worker;
        this.workDetail = workDetail;
        this.approve = approve;
        this.workStatus = workStatus;
    }

    public Boolean getExpended() {
        return expended;
    }

    public void setExpended(Boolean expended) {
        this.expended = expended;
    }
}
