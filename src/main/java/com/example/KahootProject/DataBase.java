package com.example.KahootProject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;



public class DataBase
{
    static class PlayerSalary
    {
        String rank;
        String name;
        String team;
        String salary;

        public PlayerSalary(String rank1, String name1, String team1, String salary1) {
            rank = rank1;
            name = name1;
            team = team1;
            salary = salary1;
        }

       public String toString()
       {
           return rank + ". " + name + " (" + team + ") - " + salary;
        }
    }
    public static void main(String [] args)
    {
        String url = "https://www.espn.com/nba/salaries/_/seasontype/3";

        try //in case the url does not pull correctly
        {
            Document document = Jsoup.connect(url).get(); //document represents the web page
            Elements rows = document.select("table tbody tr");

            ArrayList<PlayerSalary> salaries = new ArrayList<>();

            for(int i = 0; i < rows.size(); i++)
            {
                Element row = rows.get(i);
                Elements cols = row.select("td");

                if (cols.size() >= 4)
                {
                    String rank = cols.get(0).text();
                    String name = cols.get(1).text();
                    String team = cols.get(2).text();
                    String salary = cols.get(3).text();

                    salaries.add(new PlayerSalary(rank, name, team, salary));
                }
            }
        }
        catch (IOException e)
        {
//            e.printStackTrace();
        }

    }
}

