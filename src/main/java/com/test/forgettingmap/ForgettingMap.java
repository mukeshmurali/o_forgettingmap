package com.test.forgettingmap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ForgettingMap<K, V> {
    private ConcurrentHashMap<K, V> associationMap;
    private ConcurrentHashMap<K, Integer> accessKeyTracker;

    private int size;

    public ForgettingMap(int size) {
        this.size = size;
        this.associationMap = new ConcurrentHashMap<K, V>();
        this.accessKeyTracker = new ConcurrentHashMap<K, Integer>();
    }

    public void add(K key, V value) {
        if (associationMap.size() < size) {
            updateTrackerAndAdd(key, value);
            return;
        }
        addAfterRemovingLeastUsed(key, value);
    }

    private void updateTrackerAndAdd(K key, V value) {
        updateKeyTracker(key, 0);
        associationMap.put(key, value);
    }

    private void addAfterRemovingLeastUsed(K key, V value) {
        V keyToRemove = associationMap.remove(findLeastUsed());
        accessKeyTracker.remove(keyToRemove);
        updateTrackerAndAdd(key, value);
    }

    private K findLeastUsed() {
        return accessKeyTracker.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue)).map(Map.Entry::getKey).collect(Collectors.toList()).get(0);
    }

    public V find(K key) {
        updateKeyTracker(key, 1);
        return associationMap.get(key);
    }

    private void updateKeyTracker(K key, int value) {
        Integer callCount = Optional.ofNullable(accessKeyTracker.get(key)).orElse(0);
        callCount = callCount == 0 ? accessKeyTracker.put(key, value) : accessKeyTracker.put(key, callCount + 1);
    }

    public Set<K> getMappedKeys() {
        return associationMap.keySet();
    }
}
