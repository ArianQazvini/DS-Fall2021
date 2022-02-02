package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        ArrayList<String> allcities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            allcities.add(scanner.nextLine());
        }
        int k = Integer.parseInt(scanner.nextLine());
        int z=0;
        ArrayList<Day> days = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            String temp = scanner.nextLine();
            if(z!=0)
            {
                if(!temp.equals(days.get(z-1).getCityName()))
                {
                    Day day = new Day(temp);
                    //  day.setComplements(allcities);
                    days.add(day);
                    z++;
                }
            }
            else
            {
                Day day = new Day(temp);
                //  day.setComplements(allcities);
                days.add(day);
                z++;
            }
        }
        //  ArrayList<String> res= new ArrayList<>();
        ArrayList<String> commons= new ArrayList<>();
        for (int i = 0; i < allcities.size(); i++) {
            if(!allcities.get(i).equals(days.get(0).getCityName()))
            {
                commons.add(allcities.get(i));
            }
        }
        int count=0;
        //----------------------------
        for (int i = 1; i < days.size(); i++) {
            if(commons.contains(days.get(i).getCityName()))
            {
                deleteFrom(commons,days.get(i).getCityName());
            }
            if(commons.size()==0 )
            {
                count++;
                for (int p = 0; p < allcities.size(); p++) {
                    if(!allcities.get(p).equals(days.get(i).getCityName()))
                    {
                        commons.add(allcities.get(p));
                    }
                }
            }
        }

        System.out.println(count);

    }
    public static void deleteFrom(ArrayList<String> a , String temp)
    {
        Iterator<String> it = a.iterator();
        while (it.hasNext())
        {
            String temp2 = it.next();
            if(temp2.equals(temp))
            {
                it.remove();
                break;
            }
        }
    }



}
class Day{
    private String cityName;
    // private ArrayList<String> complements;
    public Day(String cityName){
        this.cityName= cityName;
        //  complements=new ArrayList<>();
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getCityName() {
        return cityName;
    }
}
