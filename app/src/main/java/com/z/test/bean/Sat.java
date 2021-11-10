package com.z.test.bean;

import android.location.GnssStatus;

public class Sat {

    private int PRN;
    private int type = -1;

    private double pos_x;
    private double pos_y;
    private double pos_z;

    private double E;//高度角
    private double A;//方向角

    public Sat(int PRN, int type, double e, double a) {
        this.PRN = PRN;
        this.type = type;
        E = e;
        A = a;
    }

    public int getPRN() {
        return PRN;
    }

    public void setPRN(int PRN) {
        this.PRN = PRN;
    }

    public int getType() {
        if (type >= GnssStatus.CONSTELLATION_UNKNOWN && type <= GnssStatus.CONSTELLATION_IRNSS)
            return type;
        else return GnssStatus.CONSTELLATION_UNKNOWN;
    }

    public void setType(int type) {
        if(type >= GnssStatus.CONSTELLATION_UNKNOWN && type <= GnssStatus.CONSTELLATION_IRNSS)
            this.type = type;
        else throw new IllegalArgumentException("无效的卫星类型");
    }

    public double getPos_x() {
        return pos_x;
    }

    public void setPos_x(double pos_x) {
        this.pos_x = pos_x;
    }

    public double getPos_y() {
        return pos_y;
    }

    public void setPos_y(double pos_y) {
        this.pos_y = pos_y;
    }

    public double getPos_z() {
        return pos_z;
    }

    public void setPos_z(double pos_z) {
        this.pos_z = pos_z;
    }

    public double getE() {
        return E;
    }

    public void setE(double e) {
        E = e;
    }

    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }
}
