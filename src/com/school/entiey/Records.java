package com.school.entiey;

import org.apache.taglibs.standard.extra.spath.Step;

public class Records {
    private Integer count;
    private Integer id;
    private String name;
    private String leavetime;
    private String backtime;
    private String type;

    public Records(Integer count, Integer id, String name, String leavetime, String backtime, String type) {
        this.count = count;
        this.id = id;
        this.name = name;
        this.leavetime = leavetime;
        this.backtime = backtime;
        this.type = type;
    }

    public Records() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(String leavetime) {
        this.leavetime = leavetime;
    }

    public String getBacktime() {
        return backtime;
    }

    public void setBacktime(String backtime) {
        this.backtime = backtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}