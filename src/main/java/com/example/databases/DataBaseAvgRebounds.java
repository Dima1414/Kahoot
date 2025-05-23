package com.example.databases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DataBaseAvgRebounds
{
    private static ArrayList<Choice> reboundList = new ArrayList<>();

    public DataBaseAvgRebounds()
    {

    }

    public ArrayList<Choice> getRebounds()
    {
        loadRebounds();
        return reboundList;
    }

    public static void loadRebounds()
    {
        String url = "https://www.espn.com/nba/seasonleaders/_/league/nba/sort/avgRebounds";

        try
        {
            Document document = Jsoup.connect(url).get();
            Elements rows = document.select("table tbody tr");

            for (int i = 0; i < rows.size(); i++)
            {

                Element row = rows.get(i);
                Elements cols = row.select("td");

                if (cols.size() >= 5)
                {
                    String name = cols.get(1).text();
                    String avgReboundsStr = cols.get(8).text();

                    if (name.equals("PLAYER") || avgReboundsStr.equals("RPG"))
                    {
                        continue;
                    }

                    try
                    {
                        double avgRebounds = Double.parseDouble(avgReboundsStr);
                        reboundList.add(new Choice(name, avgRebounds));
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Could not parse avg rebounds for: " + name);
                    }
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error loading data from ESPN.");
            e.printStackTrace();
        }
    }
}




