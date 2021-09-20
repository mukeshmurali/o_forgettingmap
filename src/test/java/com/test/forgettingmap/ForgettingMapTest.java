package com.test.forgettingmap;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ForgettingMapTest {
    @Test
    void addIntoForgettingMap() {
        //arrange
        int size=5;
        int value=10;
        int key=1;
        ForgettingMap fmap=new ForgettingMap(size);
        //act
        fmap.add(key,value);
        //assert
        assertThat(fmap.find(key)).isEqualTo(value);
    }
}
