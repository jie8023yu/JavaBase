package com.com.wj.program;

public class FoodBean {

    //required

    private  String foodname;

    private  int realing;

    //option

    private  int dbz;
    private  int zf;
    private  int tangfen;

    public FoodBean() {

    }

    public FoodBean(String foodname, int realing) {
        this.foodname = foodname;
        this.realing = realing;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getRealing() {
        return realing;
    }

    public void setRealing(int realing) {
        this.realing = realing;
    }

    public int getDbz() {
        return dbz;
    }

    public void setDbz(int dbz) {
        this.dbz = dbz;
    }

    public int getZf() {
        return zf;
    }

    public void setZf(int zf) {
        this.zf = zf;
    }

    public int getTangfen() {
        return tangfen;
    }

    public void setTangfen(int tangfen) {
        this.tangfen = tangfen;
    }
}
