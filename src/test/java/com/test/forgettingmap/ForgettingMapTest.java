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

    @Test
    void check_Addingmorethanmaxsize_replacesOldest_checkIfOldIsDeleted() {
        //arrange
        int size=2;
        ForgettingMap fmap=new ForgettingMap(size);
        //act
        fmap.add(1, 10);
        fmap.add(2, 20);

        //Accessing key 1 multiple times
        fmap.find(1);
        fmap.find(1);
        fmap.add(3, 30);
        //assert
        assertThat(fmap.find(2)).isNull();
    }

    @Test
    void check_Addingmorethanmaxsize_replacesOldest_checkIfNewIsAdded() {
        //arrange
        int size=2;
        ForgettingMap fmap=new ForgettingMap(size);
        //act
        fmap.add(1, 10);
        fmap.add(2, 20);

        //Accessing key 1 multiple times
        fmap.find(1);
        fmap.find(1);
        fmap.find(2);
        fmap.find(2);
        fmap.find(2);
        fmap.add(3, 30);
        //assert
        assertThat(fmap.find(3)).isEqualTo(30);
    }

    @Test
    void check_Addingmorethanmaxsize_replacesOldest_checkIfOldIsRemoved_EqualAccessCount() {
        //arrange
        int size=2;
        ForgettingMap fmap=new ForgettingMap(size);
        //act
        fmap.add(1, 10);
        fmap.add(2, 20);

        //Accessing key 1 multiple times
        fmap.find(1);
        fmap.find(2);
        fmap.find(2);
        fmap.find(1);
        fmap.add(8, 80);
        //assert
        assertThat(fmap.find(1)).isNull();
        assertThat(fmap.find(2)).isEqualTo(20);
    }

}
