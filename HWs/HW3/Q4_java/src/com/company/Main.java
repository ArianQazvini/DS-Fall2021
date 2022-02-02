//package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String firstNum;
        Scanner scanner= new Scanner(System.in);
        firstNum=scanner.nextLine();
        int k = Integer.parseInt(firstNum);
        String[] inputs = new String[k];
        for (int i = 0; i < k; i++) {
            inputs[i]=scanner.nextLine();
        }
        int size=0;
        for (int i = 0; i < k; i++) {
            String []splited = inputs[i].split(" ");
            size+=splited.length;
        }
        int [] mixed = new int[size];
        int z=0;
        for (int i = 0; i < k; i++) {
            String []splited = inputs[i].split(" ");
            for (int j = 0; j < splited.length; j++) {
                mixed[z]=Integer.parseInt(splited[j]);
                z++;
            }
        }
        //heapSort(mixed,size);
        Arrays.sort(mixed);
        for (int i = 0; i < mixed.length; i++) {
            System.out.print(mixed[i]+" ");
        }

    }
    public static void swap(int a,int b)
    {
        int temp = a;
        a=b;
        b=temp;
    }
   public static void heapify(int arr[], int n, int i)
{
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;
    if (l < n && arr[l] > arr[largest])
        largest = l;
    if (r < n && arr[r] > arr[largest])
        largest = r;
    if (largest != i) {
        int swap = arr[i];
        arr[i] = arr[largest];
        arr[largest] = swap;
        heapify(arr, n, largest);
    }
}

    // main function to do heap sort
 public static    void heapSort(int arr[], int n)
    {
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;;
            heapify(arr, i, 0);
        }
    }
}
