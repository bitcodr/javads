package org.algorithm_datastructure;

import java.util.*;

/*
 * LRUCache - Least Recently Used cache
 * - max capacity
 * -
 */
class lru_cache {
    public static void main(String[] args) {
        LRU cache = new LRU(4);
        cache.put(1);
        cache.put(2);
        cache.put(3);
        cache.put(4);
        System.out.println(cache.getAll());
        System.out.println(cache.get(2));
        System.out.println(cache.getAll());
        cache.put(5);
        System.out.println(cache.getAll());
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
        System.out.println(cache.getAll());
        
        // from left -> least recently used
        // from right -> most recently used
    }
}

class LRU {
    Set<Integer> cache;
    int capacity;

    public LRU(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashSet<>(capacity);
    }

    public Integer get(Integer key) {
        if (this.cache.contains(key)) {
            this.cache.remove(key);
        }

        this.put(key);

        return this.value(key).get();
    }

    private Optional<Integer> value(Integer key) {
        return this.cache.stream().filter(item -> item.equals(key)).findFirst();
    }

    public void put(Integer key) {
        if (this.cache.size() >= this.capacity) {
            Integer firstKey = this.cache.iterator().next();
            this.cache.remove(firstKey);
        }

        if (!this.cache.contains(key)) {
            this.cache.add(key);
        }
    }

    public Set<Integer> getAll() {
        return this.cache;
    }
}