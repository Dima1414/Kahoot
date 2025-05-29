package com.example.databases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DataBaseAvgAPG {
    private static ArrayList<Choice> assistsList;

    public DataBaseAvgAPG()
    {
        assistsList = new ArrayList<>();
    }

    public ArrayList<Choice> getAvgAPG()
    {
        loadAPGStats();
        return assistsList;
    }

    // Method to load player stats
    public static void loadAPGStats()
    {
        String url = "https://www.espn.com/nba/seasonleaders/_/league/nba/sort/avgAssists/";

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
                    String avgBlocksStr = cols.get(9).text();

                    if (name.equals("PLAYER") || avgBlocksStr.equals("BLKPG"))
                    {
                        continue;
                    }

                    try
                    {
                        double avgAssists = Double.parseDouble(avgBlocksStr);
                        assistsList.add(new Choice(name, avgAssists));
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Could not parse avg assists for: " + name);
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
