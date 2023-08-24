package com.ginwithouta.algorithm.class04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Package : com.ginwithouta.algorithm.class04
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周三
 * @Desc : T一定是非基础类型，有基础类型的需求再往外面封装一层
 */
public class Code05_HeapGreater<T> {
    private ArrayList<T> heap;
    /**
     * 反向索引表，存储每个元素被存放的位置
     */
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comparator;
    public Code05_HeapGreater(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    public boolean isEmpty() {
        return heapSize == 0;
    }
    public int size() {
        return heapSize;
    }
    public boolean contains(T item) {
        return indexMap.containsKey(item);
    }
    public T peek() {
        return heap.get(0);
    }
    public void push(T item) {
        heap.add(item);
        indexMap.put(item, heapSize);
        heapInsert(heapSize++);
    }
    public T pop() {
        T top = heap.get(0);
        swap(0, heapSize);
        indexMap.remove(top);
        heapify(0);
        return top;
    }
    public void resign(T item) {
        heapInsert(indexMap.get(item));
        heapify(indexMap.get(item));
    }
    /**
     * 删除任意位置的元素
     * @param item
     */
    public void remove(T item) {
        // 保存最后一个元素的位置
        T replace = heap.get(heapSize - 1);
        // 获取要删除的元素的位置
        int index = indexMap.get(item);
        // 删除它在索引表中的位置
        indexMap.remove(item);
        // 在数组中删除最后一个元素
        heap.remove(--heapSize);
        if (item != replace) {
            // 删除的元素不是要替换的元素，则利用刚才保存的最后一个元素进行替换
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }
    public void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }
    public void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int smaller = left + 1 < heapSize && comparator.compare(heap.get(left), heap.get(left + 1)) < 0 ? left: left + 1;
            smaller = comparator.compare(heap.get(index), heap.get(smaller)) < 0 ? index : smaller;
            if (smaller == index) {
                break;
            }
            swap(index, smaller);
            index = smaller;
            left = index * 2 + 1;
        }
    }
    public void swap(int left, int right) {
        T o1 = heap.get(left), o2 = heap.get(right);
        heap.set(right, o1);
        heap.set(left, o2);
        indexMap.put(o1, right);
        indexMap.put(o2, left);
    }
    public ArrayList<T> getAllElements() {
         return new ArrayList<>(heap);
    }

}
