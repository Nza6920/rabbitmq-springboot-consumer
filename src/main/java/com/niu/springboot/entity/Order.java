package com.niu.springboot.entity;

import java.io.Serializable;

/**
 * <功能简述>
 *
 * @author [nza]
 * @version 1.0 [2020/07/09 15:38]
 * @createTime [2020/07/09 15:38]
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 254172050745967075L;

    public Order() {
    }

    public Order(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
