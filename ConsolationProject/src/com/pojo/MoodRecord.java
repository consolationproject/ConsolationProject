package com.pojo;

public class MoodRecord {
    private Integer id;
    private String wxid;
    private Integer mrgrade;
    private String mrdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    public Integer getMrgrade() {
        return mrgrade;
    }

    public void setMrgrade(Integer mrgrade) {
        this.mrgrade = mrgrade;
    }

    public String getMrdate() {
        return mrdate;
    }

    public void setMrdate(String mrdate) {
        this.mrdate = mrdate;
    }
}
