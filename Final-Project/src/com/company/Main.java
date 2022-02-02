//package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        ArrayList<Vertex> graph = new ArrayList<>();
        // --------------------------------
        Scanner scanner = new Scanner(System.in);
        String firstLine = scanner.nextLine();
        String []firstLineSp = firstLine.split(" ");
        int vertexesN = Integer.parseInt(firstLineSp[0]);
        int edgesN = Integer.parseInt(firstLineSp[1]);
        //---------------------------
        String vertexValues = scanner.nextLine();
        String []vertexValuesSp = vertexValues.split(" ");
        for (int i = 0; i < vertexesN; i++) {
            Vertex temp = new Vertex(Integer.parseInt(vertexValuesSp[i]));
            graph.add(temp);
        }
        //----------------------------------
        for (int i = 0; i < edgesN; i++) {
            String edgeinput = scanner.nextLine();
            String []edgeinputSp = edgeinput.split( " ");
            addEdges(graph,Integer.parseInt(edgeinputSp[0]),Integer.parseInt(edgeinputSp[1]),Integer.parseInt(edgeinputSp[2]));
        }
        String st = "!";
        ArrayList<Vertex> friends = new ArrayList<>();
        while (!st.equals("exit"))
        {
            st=scanner.nextLine();
            if(st.equals("test"))
            {
                dfs(graph.get(0));
            }
            else
            {
                if(st.contains("join"))
                {
                    String []stSp = st.split(" ");
                    join(friends,graph,Integer.parseInt(stSp[1]));
                    leastFairScores(friends,graph);
                }
                else if(st.contains("left"))
                {
                    String [] stSp = st.split(" ");
                    left(friends,Integer.parseInt(stSp[1]));
                    leastFairScores(friends,graph);
                }
            }
        }










//        Vertex zero = new Vertex(0);
//        Vertex one = new Vertex(1);
//        Vertex two = new Vertex(2);
//        Vertex three = new Vertex(3);
//        Vertex four = new Vertex(4);
//        Vertex five = new Vertex(5);
//        Vertex six = new Vertex(6);
//        Vertex seven = new Vertex(7);
//        Vertex eight = new Vertex(8);
//        graph.add(zero);
//        graph.add(one);
//        graph.add(two);
//        graph.add(three);
//        graph.add(four);
//        graph.add(five);
//        graph.add(six);
//        graph.add(seven);
//        graph.add(eight);
//        //------------------------------------
//        addEdges(graph,0,1,4);
//        addEdges(graph,0,7,8);
//        addEdges(graph,7,1,11);
//        addEdges(graph,7,8,7);
//        addEdges(graph,7,6,1);
//        addEdges(graph,6,8,6);
//        addEdges(graph,6,5,2);
//        addEdges(graph,2,1,8);
//        addEdges(graph,2,8,2);
//        addEdges(graph,2,5,4);
//        addEdges(graph,2,3,7);
//        addEdges(graph,3,5,14);
//        addEdges(graph,3,4,9);
//        addEdges(graph,4,5,10);
        //-------------------------------

        //-------------------------
        //   dijkstra(graph,zero);
    }
    public static void addEdges(ArrayList<Vertex> g,int a , int b , int weight)
    {
        Edge temp = new Edge(weight);
        Vertex v1 = null;
        Vertex v2 = null;
        for (int i = 0; i < g.size(); i++) {
            if(g.get(i).getValue()==a)
            {
                v1=g.get(i);
            }
            if(g.get(i).getValue()==b)
            {
                v2= g.get(i);
            }
        }
        temp.setHead(v1);
        temp.setTail(v2);
        v1.getEdges().add(temp);
        v2.getEdges().add(temp);
    }
    public static void dfs(Vertex v)
    {
        if(v.isVisited())
        {
            return;
        }
        v.setVisited(true);
        System.out.print(v.getValue()+" ");
        for (int i = 0; i < v.getEdges().size(); i++) {
            if(v.getEdges().get(i).getHead().equals(v))
            {
                dfs(v.getEdges().get(i).getTail());
            }
            else
            {
                dfs(v.getEdges().get(i).getHead());
            }
        }
    }
    public static void resetVisited(ArrayList<Vertex> graph)
    {
        for (int i = 0; i < graph.size(); i++) {
            graph.get(i).setVisited(false);
        }
    }
    public static void relaxation(Vertex v)
    {
        for (int i = 0; i < v.getEdges().size(); i++) {
            if(v.getEdges().get(i).getHead().equals(v))
            {
                if((v.getDist()+v.getEdges().get(i).getWeight())< v.getEdges().get(i).getTail().getDist())
                {
                    v.getEdges().get(i).getTail().setDist((v.getDist()+v.getEdges().get(i).getWeight() ));
                }
            }
            else
            {
                if((v.getDist()+v.getEdges().get(i).getWeight())< v.getEdges().get(i).getHead().getDist())
                {
                    v.getEdges().get(i).getHead().setDist((v.getDist()+v.getEdges().get(i).getWeight() ));
                }
            }
        }
    }
    public static Vertex shortestDist(ArrayList<Vertex> graph)
    {
        int min = Integer.MAX_VALUE;
        Vertex temp = null;
        for (int i = 0; i < graph.size(); i++) {
            if(!graph.get(i).isVisited() && graph.get(i).getDist()<=min)
            {
                min = graph.get(i).getDist();
                temp= graph.get(i);
            }
        }
        if(temp!=null)
        {
            return temp;
        }
        else
        {
            return null;
        }
    }
    public static void dijkstra(ArrayList<Vertex> g , Vertex src)
    {
        src.setDist(0);
        for (int i = 0; i < g.size(); i++) {
            if(!g.get(i).equals(src))
            {
                g.get(i).setDist(Integer.MAX_VALUE);
            }
        }
        src.setVisited(true);
        relaxation(src);
        while (!isFinished(g))
        {
            Vertex temp = shortestDist(g);
            temp.setVisited(true);
            relaxation(temp);
        }
        resetVisited(g);
    }
    public static boolean isFinished(ArrayList<Vertex> g)
    {
        int counter = 0;
        for (int i = 0; i < g.size(); i++) {
            if(g.get(i).isVisited())
            {
                counter++;
            }
        }
        if(counter==g.size())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static void test(ArrayList<Vertex> graph)
    {
        dfs(graph.get(0));
        resetVisited(graph);
    }
    public static double fairScore(ArrayList<Vertex> inputs,ArrayList<Vertex> graph,Vertex src)
    {
        dijkstra(graph,src);
        resetVisited(graph);
        int sum = 0;
        for (int i = 0; i < inputs.size(); i++) {
            for (int j = i+1; j < inputs.size(); j++) {
                sum+= Math.abs(inputs.get(i).getDist()-inputs.get(j).getDist());
            }
        }
        if(inputs.size()==2)
        {
            return sum;
        }
        else if(inputs.size()>=2)
        {
            return (double) sum/inputs.size();
        }
        else
        {
            return -1;
        }
    }

    public static void leastFairScores(ArrayList<Vertex> inputs , ArrayList<Vertex> graph)
    {
        if(inputs.size()==1)
        {
            System.out.print(inputs.get(0).getValue()+" ");
        }
        else
        {
            double min = Double.MAX_VALUE;
            for (int i = 0; i < graph.size(); i++) {
                double temp = fairScore(inputs,graph,graph.get(i));
                if(temp!=-1)
                {
                    if(temp<min)
                    {
                        min = temp;
                    }
                }
            }
            ArrayList<Vertex> res = new ArrayList<>();
            for (int i = 0; i < graph.size(); i++) {
                double temp = fairScore(inputs,graph,graph.get(i));
                if(temp==min  && temp!=-1)
                {
                    res.add(graph.get(i));
                }
            }
            for (int i = 0; i < res.size(); i++) {
                System.out.print(res.get(i).getValue()+" ");
            }
        }
    }
    public static void join(ArrayList<Vertex> inputs ,ArrayList<Vertex> graph, int val)
    {
        for (int i = 0; i < graph.size(); i++) {
            if(graph.get(i).getValue()==val)
            {
                inputs.add(graph.get(i));
                break;
            }
        }
    }
    public static void left(ArrayList<Vertex> input,int val)
    {
        Iterator<Vertex> it = input.iterator();
        while (it.hasNext())
        {
            int x = it.next().getValue();
            if(x==val)
            {
                it.remove();
                break;
            }
        }
    }
}
class Vertex{
    private int value;
    private int dist;
    private boolean visited;
    private ArrayList<Edge> edges;
    public Vertex(int value)
    {
        this.visited=false;
        this.value=value;
        edges=new ArrayList<>();
    }

    public int getDist() {
        return dist;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public int getValue() {
        return value;
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public boolean isVisited() {
        return visited;
    }
    public void setDist(int dist) {
        this.dist = dist;
    }
    public void setValue(int value) {
        this.value = value;
    }
    @Override
    public boolean equals(Object o)
    {
        if(o==this)
        {
            return true;
        }
        if (!(o instanceof Vertex))
        {
            return false;
        }
        Vertex temp = (Vertex) o;
        if(temp.getValue()==this.getValue())
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
    private int weight;
    private Vertex head;
    private Vertex tail;
    public Edge(int weight)
    {
        this.weight=weight;
        this.head=null;
        this.tail=null;
    }

    public int getWeight() {
        return weight;
    }

    public Vertex getHead() {
        return head;
    }

    public Vertex getTail() {
        return tail;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setTail(Vertex tail) {
        this.tail = tail;
    }
    public void setHead(Vertex head) {
        this.head = head;
    }
}