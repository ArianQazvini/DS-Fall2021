package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Vertex> graph = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        PriorityQueue<Vertex> heap = new PriorityQueue<>(Comparator.comparingInt(Vertex::getDist));
        //------------------------------------
        String firstline = scanner.nextLine();
        String firstSplited[] = firstline.split(" ");
        int vertexesN = Integer.parseInt(firstSplited[0]);
        int edgesN = Integer.parseInt(firstSplited[1]);
        //------------------------
        for (int i = 0; i < vertexesN; i++) {
            Vertex v = new Vertex(i + 1);
            graph.add(v);
        }
        for (int i = 0; i < edgesN; i++) {
            String input = scanner.nextLine();
            String inputs[] = input.split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            int w = Integer.parseInt(inputs[2]);
            addedge(graph, a, b, w);
        }
        String srcString = scanner.nextLine();
        int src = Integer.parseInt(srcString);
        for (int i = 0; i < graph.size(); i++) {
            if((i+1)==src)
            {
                graph.get(i).setDist(0);
                heap.add(graph.get(i));
                break;
            }
        }
        for (int i = 0; i < graph.size(); i++) {
          if((i+1)!=src)
          {
              graph.get(i).setDist(Integer.MAX_VALUE);
          }
        }
        while (heap.size()>0)
        {
            Vertex v = heap.poll();
            for (int i = 0; i < v.getEdges().size(); i++) {
                if (v.getEdges().get(i).getEndV().getDist() > (v.getDist() + v.getEdges().get(i).getWeight()))
                {
                    v.getEdges().get(i).getEndV().setDist(v.getDist()+v.getEdges().get(i).getWeight());
                    Vertex temp = new Vertex(v.getValue());
                    temp.setDist(v.getEdges().get(i).getEndV().getDist());
                    heap.add(temp);
                }
            }
        }
        for (int i = 0; i < graph.size(); i++) {
            if((i+1)==Integer.MAX_VALUE)
            {
                System.out.print(-1 + " ");
            }
            else
            {
                System.out.print(graph.get(i).getDist()+" ");
            }
        }



























//
//
//
//
//
//
//
//        Vertex zero = new Vertex(0);
//        Vertex one = new Vertex(1);
//        Vertex two = new Vertex(2);
//        Vertex three = new Vertex(3);
//        Vertex four = new Vertex(4);
//        Vertex five = new Vertex(5);
//        Vertex six = new Vertex(6);
//        Vertex seven = new Vertex(7);
//        Vertex eight = new Vertex(8);
//        //------------------------------------
//        addedge(zero,one,4);
//        addedge(zero,seven,8);
//        addedge(seven,one,11);
//        addedge(seven,eight,7);
//        addedge(seven,six,1);
//        addedge(six,eight,6);
//        addedge(six,five,2);
//        addedge(two,one,8);
//        addedge(two,eight,2);
//        addedge(two,five,4);
//        addedge(two,three,7);
//        addedge(three,five,14);
//        addedge(three,four,9);
//        addedge(four,five,10);
//        //-------------------------------
//        graph.add(zero);
//        graph.add(one);
//        graph.add(two);
//        graph.add(three);
//        graph.add(four);
//        graph.add(five);
//        graph.add(six);
//        graph.add(seven);
//        graph.add(eight);
//        //-----------------------
//        for (int i = 0; i < graph.size(); i++) {
//            if(i==0)
//            {
//                for (int j = 0; j < graph.get(i).getEdges().size(); j++) {
//                    if(graph.get(i).getEdges().get(j).getHead().equals(graph.get(i)))
//                    {
//                        graph.get(i).getEdges().get(j).getTail().setDist(graph.get(i).getEdges().get(j).getWeight());
//                    }
//                    else
//                    {
//                        graph.get(i).getEdges().get(j).getHead().setDist(graph.get(i).getEdges().get(j).getWeight());
//                    }
//                }
//            }
//            else if(i!=1 && i!= 7)
//            {
//                graph.get(i).setDist(Integer.MAX_VALUE);
//            }
//        }
//        graph.get(0).setVisited(true);
//        while (!isFinished(graph))
//        {
//            Vertex temp = smallestDist(graph);
//            temp.setVisited(true);
//            relaxation(temp);
//        }
//        System.out.println("slm");
    }
    public static void addedge(ArrayList<Vertex> g,int  src , int  end , int weight)
    {
        int temp1=0, temp2=0;
        for (int i = 0; i < g.size(); i++) {
            if((i+1)==src)
            {
                temp1=i;
            }
            if((i+1)==end)
            {
                temp2=i;
            }
        }
        Edge temp = new Edge(weight);
        temp.setEndV(g.get(temp2));
        g.get(temp1).getEdges().add(temp);
    }
}
class Vertex{
    private int value;
    private int dist;
    private boolean visited;
    private ArrayList<Edge> edges;
    public Vertex(int value) {
        this.value = value;
        this.edges=new ArrayList<>();
        visited= false;
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public void setDist(int dist) {
        this.dist = dist;
    }
    public int getValue() {
        return value;
    }
    public ArrayList<Edge> getEdges() {
        return this.edges;
    }
    public boolean isVisited() {
        return visited;
    }
    public int getDist() {
        return dist;
    }
    @Override
    public boolean equals(Object o)
    {
        if(o==this)
        {
            return true;
        }
        if(!(o instanceof Vertex))
        {
            return false;
        }
        Vertex v =(Vertex) o;
        if(v.getValue()==this.getValue())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
class Edge{
    private Vertex endV;
    private int weight;
    public Edge(int weight) {
        this.weight = weight;
    }
    public void setEndV(Vertex endV) {
        this.endV = endV;
    }
    public Vertex getEndV() {
        return endV;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
}