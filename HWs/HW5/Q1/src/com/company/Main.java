//package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static int curr;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n =Integer.parseInt(scanner.nextLine());
        ArrayList<Node> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Node temp = new Node(i+1);
            graph.add(temp);
        }
        for (int i = 0; i < n-1; i++) {
            String input = scanner.nextLine();
            String []strings = input.split(" ");
            addedge(graph,Integer.parseInt(strings[0]),Integer.parseInt(strings[1]));
        }
        System.out.println(maxProductOfTwoPaths2(graph));
    }
    //*********************************************************
    public static void addedge(ArrayList<Node> g ,int a , int b)
    {
        Node temp = null;
        if(a<b)
        {
            for (int i = 0; i < g.size(); i++) {
                if(g.get(i).getValue()==a)
                {
                    temp = g.get(i);
                }
                else if(g.get(i).getValue()==b)
                {
                    g.get(i).getNeighbours().add(temp);
                    temp.getNeighbours().add(g.get(i));
                    break;
                }
            }
        }
        else
        {
            for (int i = 0; i < g.size(); i++) {
                if(g.get(i).getValue()==b)
                {
                    temp = g.get(i);
                }
                else if(g.get(i).getValue()==a)
                {
                    g.get(i).getNeighbours().add(temp);
                    temp.getNeighbours().add(g.get(i));
                    break;
                }
            }
        }

    }
    public static int dfs(Node v,int exclude)
    {
        if(v.isVisited())
        {
            return 0;
        }
        v.setVisited(true);
        int longestp=0;
        for (int i = 0; i < v.getNeighbours().size(); i++) {
            if(v.getNeighbours().get(i).getValue()==exclude)
            {
                continue;
            }
            longestp= Math.max(longestp,dfs(v.getNeighbours().get(i), v.getValue())+1);
        }
        return longestp;
    }
    public static int dfs2(Node v,int exclude)
    {
        int max1 = 0, max2 = 0, total = 0;
        for (int i = 0; i < v.getNeighbours().size(); i++) {
            if(v.getNeighbours().get(i).getValue()==exclude)
            {
                continue;
            }
            total = Math.max(total, dfs2(v.getNeighbours().get(i), v.getValue()));
            if (curr > max1)
            {
                max2 = max1;
                max1 = curr;
            }
            else
                max2 = Math.max(max2, curr);
        }
        total = Math.max(total, max1 + max2);
        curr = max1 + 1;
        return total;
    }
    public static int maxProductOfTwoPaths2(ArrayList<Node> graph)
    {
        int res=Integer.MIN_VALUE, path1,path2;
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.get(i).getNeighbours().size(); j++) {
                curr=0;
                path1 = dfs(graph.get(i),graph.get(i).getNeighbours().get(j).getValue());
                graph.get(i).setVisited(false);
                graph.get(i).getNeighbours().get(j).setVisited(false);
                curr=0;
                path2 = dfs(graph.get(i).getNeighbours().get(j),graph.get(i).getValue());
                res = Math.max(res,path1*path2);
                graph.get(i).setVisited(false);
                graph.get(i).getNeighbours().get(j).setVisited(false);
            }
        }
        return res;
    }
}
class Node{
    private int value;
    private ArrayList<Node> neighbours;
    private boolean visited;
    public Node(int value)
    {
        this.value=value;
        this.visited = false;
        neighbours = new ArrayList<>();
    }
    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }
    public void removeNeighbour(int valuue)
    {
        Iterator<Node> it = this.neighbours.iterator();
        while (it.hasNext())
        {
            int x = it.next().value;
            if(x==valuue)
            {
                it.remove();
                break;
            }
        }
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public int getValue() {
        return value;
    }

    public boolean isVisited() {
        return visited;
    }
}