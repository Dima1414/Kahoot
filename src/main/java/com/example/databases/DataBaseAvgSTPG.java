package com.example.databases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DataBaseAvgSTPG {
    private static ArrayList<Choice> STPGList = new ArrayList<>();

    public DataBaseAvgSTPG() {
        STPGList = new ArrayList<>();
    }


    public ArrayList<Choice> getSTPGList() {
        loadSTPGList();
        Collections.shuffle(STPGList);
        return STPGList;
    }

    public static void loadSTPGList() {
        String url = "https://www.espn.com/nba/seasonleaders/_/league/nba/sort/avgSteals";

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
                    String avgSTPG = cols.get(10).text();

                    if (name.equals("PLAYER") || avgSTPG.equals("STPG"))
                    {
                        continue;
                    }

                    try

                    {
                        double avgSTPGList = Double.parseDouble(avgSTPG);
                        STPGList.add(new Choice(name, avgSTPGList));
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