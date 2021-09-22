package com.test.forgettingmap;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class MultithreadTest {

    @Test
    void addIntoForgettingMap_Remove() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        //arrange
        int size = 10000;
        ForgettingMap fmap = new ForgettingMap(size);
        //act
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName());
            fmap.add(Thread.currentThread().getName(),90);
        };

        ExecutorService threadPool = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(item -> threadPool.execute(task));
        threadPool.shutdown();

        //Assert that each threads keys are added into the map
        for(Object key: fmap.getMappedKeys()){
            assertThat(fmap.find(key)).isEqualTo(90);
        }
    }
}
