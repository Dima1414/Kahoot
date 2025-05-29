package com.example.databases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DataBaseAvgBLKPG
{
    private static ArrayList<Choice> blocksList;

    // Constructor directly under the class name
    public DataBaseAvgBLKPG()
    {
        blocksList = new ArrayList<>();
    }

    public ArrayList<Choice> getAvgBLKPG()
    {
        loadBLKPGStats();
        return blocksList;
    }

    // Method to load player stats
    public static void loadBLKPGStats()
    {
        String url = "https://www.espn.com/nba/seasonleaders/_/league/nba/sort/avgBlocks";

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
                    String avgBlocksStr = cols.get(11).text();

                    if (name.equals("PLAYER") || avgBlocksStr.equals("BLKPG"))
                    {
                        continue;
                    }

                    try
                    {
                        double avgBlocks = Double.parseDouble(avgBlocksStr);
                        blocksList.add(new Choice(name, avgBlocks));
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

