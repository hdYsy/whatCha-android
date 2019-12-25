package com.lianjie.andorid.bean;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/9/24
 * Time: 15:56
 */
public class LatLngBean {
    private String address;
    private double lat;
    private double lon;
    private String district;

    public LatLngBean(String address, double lat, double lon, String district) {
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        this.district = district;
    }


    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
