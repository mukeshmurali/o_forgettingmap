package com.test.forgettingmap;

import java.util.HashMap;

public class ForgettingMap<K,V> {
    private HashMap<K, V> associationMap;
    private int size;

    public ForgettingMap(int size) {
        this.size = size;
        this.associationMap=new HashMap<K,V>();
    }

    public void add(K key, V value) {
        associationMap.put(key,value);
    }

    public V find(K key) {
        return associationMap.get(key);
    }
}
