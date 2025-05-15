package com.example.KahootProject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class DataBase
{
    public static void main(String [] args)
    {
        String url = "https://www.espn.com/nba/salaries/_/seasontype/3";

        try //in case the url does not pull correctly
        {
            Document document = Jsoup.connect(url).get(); //document represents the web page
//            Elements books = document.select();
            //ffeiojej
        }
        catch (IOException e)
        {
//            e.printStackTrace();
        }

    }
}

