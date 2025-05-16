package com.example.KahootProject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DataBaseSalaries
{
    // Class to hold player name and salary together
    public static class PlayerSalary
    {
        public String name;
        public String salary;

        public PlayerSalary(String name1, String salary1) {
            name = name1;
            salary = salary1;
        }
    }

    // Static list to store players and salaries
    public static ArrayList<PlayerSalary> salaries = new ArrayList<>();

    public static void loadSalaries()
    {
        String url = "https://www.espn.com/nba/salaries/_/seasontype/3";

        try
        {
            Document document = Jsoup.connect(url).get();
            Elements rows = document.select("table tbody tr");

            for (int i = 0; i < rows.size(); i++)
            {
                Element row = rows.get(i);
                Elements cols = row.select("td");

                if (cols.size() >= 4)
                {
                    String name = cols.get(1).text();     // 2nd column is player name
                    String salary = cols.get(3).text();   // 4th column is salary

                    salaries.add(new PlayerSalary(name, salary));
                }
            }

        }
        catch (IOException e)
        {
            System.out.println("Error: Unable to retrieve data from the site.");
            e.printStackTrace();
        }
    }

}
