package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n=0;
        String pushes =null,pops=null;
        for (int i = 0; i < 3; i++) {
            String input = scanner.nextLine();
            if(i==0){
                n =Integer.parseInt(input);
            }
            else if(i==1)
            {
                pushes=input;
            }
            else if(i==2)
            {
                pops=input;
            }
        }
        //---------------------------------
        String pushesSp[] = pushes.split(" ");
        String popsSp[] = pops.split(" ");
        int pushArr[]=new int[n];
        int popArr[]=new int[n];
        for (int i = 0; i < n; i++) {
            pushArr[i]=Integer.parseInt(pushesSp[i]);
            popArr[i]=Integer.parseInt(popsSp[i]);
        }
        Stack stack = new Stack(n);
        Stack popStack = new Stack(n);
        for (int i = n-1; i >= 0; i--) {
            popStack.push(popArr[i]);
        }
        for (int i = 0; i < n; i++) {
            stack.push(pushArr[i]);
            while (stack.peek()== popStack.peek() && (stack.peek() != -1 && popStack.peek()!= -1))
            {
                stack.pop();
                popStack.pop();
            }
        }
        if(stack.isEmpty())
        {
            System.out.println("true");
        }
        else
        {
            System.out.println("false");
        }
    }
}
class Stack{
    private int size;
    private int []items;
    private int top = -1;
    private boolean isEmpty = true;
    public Stack(int size) {
        this.size = size;
        this.items = new int[size];
    }
    public int getSize() {
        return size;
    }
    public int[] getItems() {
        return items;
    }
    public void push(int data)
    {
        if(top > (size-1))
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
