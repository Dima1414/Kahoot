package com.example.databases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DataBaseLakersWeight
{
    private static ArrayList<Choice> lakersWeightList;

    public DataBaseLakersWeight()
    {
        lakersWeightList = new ArrayList<>();
    }

    public ArrayList<Choice> getLakersWeight()
    {
        loadPoints();
        return lakersWeightList;
    }

    public static void loadPoints()
    {
        String url = "https://www.espn.com/nba/team/roster/_/name/lal/los-angeles-lakers";

        try
        {
            Document doc = Jsoup.connect(url).get();

            Elements rows = doc.select("table tbody tr");

            for (int i = 0; i < rows.size(); i++)
            {
                Element row = rows.get(i);
                Elements cols = row.select("td");

                if (cols.size() > 3)
                {
                    String name = cols.get(1).text().replaceAll("\\d", "");
                    String weight = cols.get(5).text().replaceAll("[ lbs]", "");

                    if (name.equals("NAME") || weight.equals("WT"))
                    {
                        continue;
                    }

                    lakersWeightList.add(new Choice(name, Double.parseDouble(weight)));
                    System.out.println(name + " " + weight);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error loading points data.");
            e.printStackTrace();
        }
    }
}
