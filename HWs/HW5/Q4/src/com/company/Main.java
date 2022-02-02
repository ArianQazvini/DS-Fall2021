package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int students,vahed;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String inputs[] = input.split(" ");
        vahed = Integer.parseInt(inputs[0]);
        students = Integer.parseInt(inputs[1]);
        //--------------------------------
        System.out.println(func2(vahed,students));

    }

    public static int func(int start,int end , int stn,int counter)
    {
        if(stn==1)
        {
           // counter+=(end-start)+1;
            return counter;
        }
        else
        {
            if(start==end)
            {
                counter++;
                return counter;
            }
            else if(end-start==1)
            {
                counter+=2;
                return counter;
            }
            int mid = (start+end)/2;
            counter++;
           int up= func(mid+1,end,stn,counter);
           int down = func(start,mid-1,stn-1,counter);
           if(up>down)
           {
               return up;
           }
           else
           {
               return down;
           }
        }
    }
    public static int func2(int vahed,int stn)
    {
        int[][] table = new int[stn+1][vahed+1];
        for (int i = 1; i <=stn ; i++) {
       //     table[i][0]=0;
            table[i][1]=1;
        }//1 vahed or 0 vahed
        for (int i = 1; i <=vahed ; i++) {
            table[1][i]=i;
        }//1 student
        int temp =0;
        for (int i = 2; i <= stn; i++) {
            for (int j = 2; j <= vahed; j++) {
                table[i][j] = Integer.MAX_VALUE;
                for (int z = 1; z <= j; z++) {
                    if(table[i-1][z-1]>table[i][j-z])
                    {
                        temp= table[i-1][z-1]+1;
                    }
                    else
                    {
                        temp= table[i][j-z]+1;
                    }
                    if (temp < table[i][j])
                        table[i][j] = temp;
                }
            }
        }
        return table[stn][vahed];
    }
}
