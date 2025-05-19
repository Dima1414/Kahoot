package com.example.KahootProject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DataBaseNetWorth
{
    private String name;
    private String netWorth;
    public static ArrayList<DataBaseNetWorth> netWorthList;

    public DataBaseNetWorth(String name, String netWorth)
    {
        this.name = name;
        this.netWorth = netWorth;
        netWorthList = new ArrayList<DataBaseNetWorth>();
    }



    public static void loadNetWorths()
    {
        String url = "https://www.celebritynetworth.com/list/top-50-nba/";

        try
        {
            Document doc = Jsoup.connect(url).get(); //connects the HTML source to java
            Elements players = doc.select("div.listing"); //Element stores the

            for (int i = 0; i < players.size(); i++)
            {
                Element player = players.get(i);

                Element nameElement = player.selectFirst("h2 a");
                Element netWorthElement = player.selectFirst("div.value");

                if (nameElement != null && netWorthElement != null)
                {
                    String name = nameElement.text();
                    String netWorth = netWorthElement.text();
                    netWorthList.add(new DataBaseNetWorth(name, netWorth));
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error loading net worth data.");
            e.printStackTrace();
        }
    }
}

