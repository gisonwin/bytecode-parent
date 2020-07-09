package com.yzmedu.gisonwin.chap01;

import lombok.*;

import static java.lang.Math.PI;

/**
 * @author <a href="mailto:gisonwin@qq.com">GisonWin</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Roundness implements Shape {
    private double radius;//半径

    @Override
    public Double area() throws Exception {
        return PI * radius * radius;
    }
}
