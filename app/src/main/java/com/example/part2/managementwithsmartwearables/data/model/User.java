package com.example.part2.managementwithsmartwearables.data.model;

public class User {
    int userIdx;
    String userId;
    String name;
    String profile;

    public User() {
    }

    public User(int userIdx, String userId, String name, String profile) {
        this.userIdx = userIdx;
        this.userId = userId;
        this.name = name;
        this.profile = profile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(int userIdx) {
        this.userIdx = userIdx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
