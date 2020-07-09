package com.yzmedu.gisonwin.chap01;

import lombok.Builder;
import lombok.Builder.Default;

/**
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Getter
//@Setter
@Builder
public class Rectangle implements Shape, Paint {
    private double width;//宽
    private double height;//高
    @Default
    private String color = "green";

    @Override
    public Double area() {
        return width * height;
    }

    /***
     * 涂色接口定义 涂色功能.
     * @throws RuntimeException e
     */
    @Override
    public void painting() throws RuntimeException {
        System.out.println("The rectangle is painted " + this.color);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
