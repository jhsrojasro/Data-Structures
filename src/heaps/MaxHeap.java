/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heaps;

/**
 *
 * @author Sebastian Rojas
 */
public class MaxHeap<T extends Comparable <?super T>> {
    int maxSize;
    int size;
    T[] array;
    
    public MaxHeap(int maxSize){
        this.maxSize = maxSize;
        this.size = 0;
        this.array = (T[])new Comparable[this.maxSize];
        
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public T getMax(){
        return array[0];
    }
    
    public T extractMax(){
        T result = array[0];
        size--;
        array[0] = array[size];
        siftDown(0);
        return result;
    }
    
    public void siftUp(int index){
        T temp = array[index];
        while(index > 0 && array[parent(index)].compareTo(array[index]) < 0){
            array[index] = array[parent(index)];
            index = parent(index);
        }
        array[index] = temp;
    }
    
    public void siftDown(int index){
        int maxIndex = index;
        int l = leftChild(index);
        if(l <= size && array[l].compareTo(array[maxIndex]) > 0) maxIndex = l;
        int r  = rightChild(index);
        if(r <= size && array[r].compareTo(array[maxIndex]) > 0) maxIndex = r;
        if(index != maxIndex){
            swap(index, maxIndex);
            siftDown(maxIndex);
        }
    }
    
    public void insert(T data){
        if(size == maxSize) throw new RuntimeException("La cola está llena");
        else{
            array[size] = data;
            siftUp(size);
            size++;
        }
    }
    
    private void swap(int x, int y){
        T temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
    
    private int parent(int index){
        return (index-1)/2;
    }
    
    private int leftChild(int index){
        return 2*index + 1;
    }
    
    private int rightChild(int index){
        return (index+1)*2;
    }
    
    public static void main(String[] args) {
        MaxHeap<Integer> heap = new MaxHeap<Integer>(13);
        heap.insert(50);
        heap.insert(100);
        System.out.println(heap.getMax());
        heap.insert(101);
        System.out.println(heap.extractMax());
        System.out.println(heap.getMax());
    }
}
