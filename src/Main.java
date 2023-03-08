/**Paul Overfelt
 * paul@overfelt.net
 * Power of 2 Min Heap priority queue. Min heap with 2^x number of childern
 * Made for The Forage Walmart Global Tech Task 1 Advanced Data Structures
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 * This is a priority queue min heap, where the samller amounts are higher in priority
 * 0 is max priority
 */
class PowerOfTwoMinHeap {
    private int[] heap;
    private int size;
    private int numChildren;

    //Constructor
    public PowerOfTwoMinHeap(int numChildrenPowerOfTwo, int capacity) {
        this.numChildren = (int) Math.pow(2, numChildrenPowerOfTwo);
        this.size = 0;
        this.heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }

    public boolean isFull() {
        return heap.length == size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Finds the parent index of given child index
     */
    private int parentIndex(int i){
        return (int) Math.floor((i - 1)/numChildren);
    }

    /**
     * Inserts given priority value at the end of the array then calls heapifyUp to bubble up the value.
     */
    public void insert(int insertValue) {
        if (isFull()) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size++] = insertValue;
        heapifyUp(size - 1);
    }

    /**
     * Returns the item a index 0 as it is the lowest number and has the highest priority.
     */
    public int pop_max() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        int maxValue = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = -1;
        size--;
        heapifyDown(0);
        return maxValue;
    }

    /**
     * Recursively calls itself to bubble up the value at the given index
     */
    private void heapifyUp(int index) {
        if (index != 0) {
            int parentIndex = parentIndex(index);
            if (heap[parentIndex] > heap[index]) {
                int tempValue = heap[parentIndex];
                heap[parentIndex] =  heap[index];
                heap[index] = tempValue;
                heapifyUp(parentIndex);
            }
        }
    }

    /**
     * Recursively calls itself to bubble down the value at the given index
     */
    private void heapifyDown(int index) {
        if (index < size -1) {
            int minChildIndex = index;
            for (int i = index + 1; i <=numChildren * index + numChildren; i++ ){ // loop through all children
                if (heap[i] < heap[minChildIndex]) {
                    if (heap[i] != -1) { // ignore default -1 value in array
                        minChildIndex = i;
                    }
                }
            }
            int tempValue = heap[minChildIndex];
            heap[minChildIndex] = heap[index];
            heap[index] = tempValue;
            if (minChildIndex != index) { // stop the recursion if the index is already the smallest
                heapifyDown(minChildIndex);
            }
        }
    }

    /**
     * Prints all data in the heap, ignoring the default values.
     */
    public void printHeap() {
        System.out.println("\nHeap = ");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] +" ");
        }
        System.out.println();
    }
}



// Driver - Tester
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Power Of Two Max Heap Test");
        System.out.println("Please enter the power of two children (2^x) for each node and size of heap");
        PowerOfTwoMinHeap heapPower2 = new PowerOfTwoMinHeap(scan.nextInt(), scan.nextInt());

        char cont;
        do {
            System.out.println("\n Power Of 2 Max Heap Functions");
            System.out.println("1. Insert");
            System.out.println("2. Pop Max");
            System.out.println("3. Fill Heap");

            int choice = scan.nextInt();
            switch (choice){
                case 1 :
                    try {
                        System.out.println("Enter number to insert");
                        heapPower2.insert(scan.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2 :
                    try {
                        int max = heapPower2.pop_max();
                        System.out.println("Max number is : " + max);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Filling Heap");
                        heapPower2.insert(10);
                        heapPower2.insert(12);
                        heapPower2.insert(88);
                        heapPower2.insert(83);
                        heapPower2.insert(9);
                        heapPower2.insert(33);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    }
            }
            heapPower2.printHeap();
            System.out.println("\n Do you want to continue (Y or N");
            cont = scan.next().charAt(0);
        } while (cont == 'Y' || cont == 'y');
    }
}