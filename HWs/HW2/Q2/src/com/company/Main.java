package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
         String command= scanner.nextLine();
         String []numsString= command.split(" ");
         Stack inputs= new Stack(numsString.length);
         for (int i = numsString.length-1; i >= 0; i--) {
             inputs.push(Integer.parseInt(numsString[i]));
         }

         Stack stack = new Stack(numsString.length);
         ArrayList<Integer> temp = new ArrayList<>();
         int j =1;
         while (j!= numsString.length) {
             if(inputs.peek()==j)
             {
                 temp.add(inputs.pop());
                 j++;
             }
             else if(stack.peek() == j)
             {
                 temp.add(stack.pop());
                 j++;
             }
             else
             {
                 if(inputs.peek()==-1)
                 {
                     break;
                 }
                 else
                 {
                     stack.push(inputs.pop());
                 }
             }
         }
         while (!stack.isEmpty())
         {
             temp.add(stack.pop());
         }
         boolean isValid = true;
         for (int i = 0; i < temp.size(); i++) {
             if(temp.get(i)!= (i+1))
             {
                 isValid = false;
                 break;
             }
         }
         if(isValid)
         {
             System.out.println("yes");
         }
         else
         {
             System.out.println("no");
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
