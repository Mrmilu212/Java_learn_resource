package com.itheima.domain;

import java.io.Serial;
import java.io.Serializable;

public class GameInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 4415979582620790632L;

    private int[][] data;
    private int x;
    private int y;
    private int step;
    private String path;

    public GameInfo() {
    }

    public GameInfo(int[][] data, int x, int y, int step, String path) {
        this.data = data;
        this.x = x;
        this.y = y;
        this.step = step;
        this.path = path;
    }

    /**
     * 获取
     * @return data
     */
    public int[][] getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(int[][] data) {
        this.data = data;
    }

    /**
     * 获取
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * 设置
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 获取
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * 设置
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 获取
     * @return step
     */
    public int getStep() {
        return step;
    }

    /**
     * 设置
     * @param step
     */
    public void setStep(int step) {
        this.step = step;
    }

    /**
     * 获取
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    public String toString() {
        return "GameInfo{data = " + data + ", x = " + x + ", y = " + y + ", step = " + step + ", path = " + path + "}";
    }
}
