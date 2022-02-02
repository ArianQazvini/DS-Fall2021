//package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List list  = new List();
        Scanner scanner = new Scanner(System.in);
        String nS = scanner.nextLine();
        int n = Integer.parseInt(nS);
        for (int i = 0; i <n; i++) {
            String command = scanner.nextLine();
            if(command.contains("push_back"))
            {
                if(list.size()>=50000)
                {
                    System.out.println("No more space :(");
                }
                else
                {
                    String [] splited = command.split(" ");
                    if(list.isReversed())
                    {
                        list.toFront(Integer.parseInt(splited[1]));
                    }
                    else
                    {
                        list.toEnd(Integer.parseInt(splited[1]));
                    }
                }
            }
            else if(command.contains("to_front"))
            {
                if(list.size()>=50000)
                {
                    System.out.println("No more space :(");
                }
                else
                {
                    String [] splited = command.split(" ");
                    if(list.isReversed())
                    {
                        list.toEnd(Integer.parseInt(splited[1]));
                    }
                    else
                    {
                        list.toFront(Integer.parseInt(splited[1]));
                    }
                }
            }
            else if(command.equals("back"))
            {
                if(list.size()<=0)
                {
                    System.out.println("No job :)");

                }
                else
                {
                    if(list.isReversed())
                    {
                        System.out.println(Integer.toString(list.front()));
                    }
                    else {
                      System.out.println(Integer.toString(list.back()));
                    }
                }
            }
            else if(command.equals("front"))
            {
                if(list.size()<=0)
                {
                    System.out.println("No job :)");
                }
                else
                {
                    if(list.isReversed())
                    {
                        System.out.println(Integer.toString(list.back()));
                    }
                    else {
                        System.out.println(Integer.toString(list.front()));
                    }
                }
            }
            else if(command.equals("reverse"))
            {
                list.reverse();
            }
        }
    }
}
class List{
    static Node head=null;
    static Node tail = head;
    private int size=0;
    private boolean isReversed = false;
    public boolean isReversed() {
        return isReversed;
    }
    private class Node{
        private int data;
        public Node next;
        public Node prev;
        public Node(int data) {
            this.data = data;
        }
        public int getData() {
            return data;
        }
        public void setData(int data) {
            this.data = data;
        }
    }
    public void toEnd(int data)
    {
        size++;
        if(head ==null)
        {
            Node node = new Node(data);
            node.next=head;
            node.prev= null;
            head=node;
            tail=head;
        }
        else
        {
              Node node = new Node(data);
              tail.next= node;
              node.next=null;
              node.prev = tail;
              tail = node;
        }
    }
    public void toFront(int data)
    {
        size++;
        if(head==null)
        {
            Node node = new Node(data);
            node.next=head;
            node.prev= null;
            head=node;
            tail=head;
        }
        else
        {
            Node node = new Node(data);
            node.next=head;
            node.prev =null;
            head.prev = node;
            head=node;
        }
    }
    public int front()
    {
        size--;
        int temp2 = head.data;
        Node temp = head;
        if(temp.next==null)
        {
            head=null;
            tail=null;
            return temp2;
        }
        else
        {
            head=head.next;
            temp=null;
            return temp2;
        }
    }
    public int back()
    {
        size--;
        if(head.next==null)
        {
            int temp = head.data;
            head=null;
            return temp;
        }
        else
        {
            int num = tail.data;
            Node temp =tail;
            tail = tail.prev;
            tail.next=null;
            temp=null;
            return num;
        }
    }
    public void reverse()
    {
        if(isReversed && size>=2)
        {
            isReversed=false;
        }
        else if(!isReversed && size >=2)
        {
            isReversed =true;
        }
    }
    public int size()
    {
        return size;
    }
}




























































































































