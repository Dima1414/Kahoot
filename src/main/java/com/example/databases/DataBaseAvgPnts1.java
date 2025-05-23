package com.example.databases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DataBaseAvgPnts1 extends Choice
{
    private static ArrayList<Choice> playerStatsList = new ArrayList<>();

    public DataBaseAvgPnts1(String name, double stat)
    {
        super(name, stat);
    }

    public static ArrayList<Choice> getAvgPointsList()
    {
        loadPlayerStats();
        return playerStatsList;
    }

    public static void loadPlayerStats()
    {
        String url = "https://www.espn.com/nba/seasonleaders/_/league/nba/sort/avgPoints";

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
                    String avgPointsStr = cols.get(13).text();

                    if (name.equals("PLAYER") || avgPointsStr.equals("PTS"))
                    {
                        continue;
                    }

                    try
                    {
                        double avgPoints = Double.parseDouble(avgPointsStr);
                        playerStatsList.add(new Choice(name, avgPoints));
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

