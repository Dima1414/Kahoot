package com.example.KahootProject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DataBaseAvePnts
{
    private String name;
    private double avgPoints;
    public static ArrayList<DataBaseAvePnts> playerStatsList;

    // Constructor directly under the class name
    public DataBaseAvePnts(String name, double avgPoints)
    {
        this.name = name;
        this.avgPoints = avgPoints;
        playerStatsList = new ArrayList<DataBaseAvePnts>();
    }

    // Method to load player stats
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
                        playerStatsList.add(new DataBaseAvePnts(name, avgPoints));
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

