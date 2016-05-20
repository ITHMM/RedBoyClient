package com.itheima.redboyclient.bean;

/**
 * Created by ly on 2016/4/3.
 */
public class Product {
    private String name;
    private String color;
    private String pic;
    private String size;
    private String price;

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getPic() {
        return pic;
    }

    public String getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Product{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", size='" + size + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
