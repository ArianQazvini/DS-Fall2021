package com.company;

public class Main {
    public static Stack full = new Stack(1000);
    public static Stack empty = new Stack(1000);
    public static void main(String[] args) {

    }
    public static void enQueue(int data)
    {
        if(full.isEmpty() && empty.isEmpty())
        {
            full.push(data);
        }
        else{
            while (!full.isEmpty())
            {
                empty.push(full.pop());
            }
            full.push(data);
            while (!empty.isEmpty())
            {
                full.push(empty.pop());
            }
        }
    }
    public static int deQueue()
    {
        return full.pop();
    }
}
class Stack{
    private int size=0;
    private int capacity;
    private int []items;
    private int top = -1;
    private boolean isEmpty = true;
    public Stack(int capacity) {
        this.capacity = capacity;
        this.items = new int[capacity];
    }
    public int getSize() {
        return top+1;
    }
    public int[] getItems() {
        return items;
    }
    public void push(int data)
    {
        if(top > (capacity-1))
        {
        }
        else
        {
            top++;
            items[top]= data;
        }
    }
    public int pop()
    {
        if(top<0)
        {
            return -1;
        }
        else
        {

            int x = items[top--];
            return x;
        }
    }
    public int peek()
    {
        if(top <0)
        {
            return -1;
        }
        else
        {
            return items[top];
        }
    }

    public boolean isEmpty() {
        if(top < 0)
        {
            isEmpty= true;
            return isEmpty;
        }
        else
        {
            isEmpty=false;
            return isEmpty;
        }
    }
}
