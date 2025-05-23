package com.example.databases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DataBaseAvgThreePoints
{
    private static ArrayList<Choice> threePntsList = new ArrayList<>();

    public DataBaseAvgThreePoints()
    {

    }

    public ArrayList<Choice> getRebounds()
    {
        loadThreePnts();
        return threePntsList;
    }

    public static void loadThreePnts()
    {
        String url = "https://www.espn.com/nba/seasonleaders/_/league/nba/sort/avgThreePointFieldGoalsMade";

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
                    String avgThreesStr = cols.get(7).text();

                    if (name.equals("PLAYER") || avgThreesStr.equals("3PM"))
                    {
                        continue;
                    }

                    try
                    {
                        double avgThrees = Double.parseDouble(avgThreesStr);
                        threePntsList.add(new Choice(name, avgThrees));
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Could not parse avg points for: " + name);
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

