package com.zhuazhuale.changsha.module.home.Bean;

/**
 * 网络强度的实体类
 * Created by Administrator on 2018/1/2 0002.
 */

public class WifiData {
    //ssid, signalLevel, speed, units
    String ssid;
    int signalLevel;
    int speed;
    String units;

    public WifiData(String ssid, int signalLevel, int speed, String units) {
        this.ssid = ssid;
        this.signalLevel = signalLevel;
        this.speed = speed;
        this.units = units;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public int getSignalLevel() {
        return signalLevel;
    }

    public void setSignalLevel(int signalLevel) {
        this.signalLevel = signalLevel;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
