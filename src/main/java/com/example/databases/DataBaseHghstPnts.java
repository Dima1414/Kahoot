package com.example.databases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

    public class DataBaseHghstPnts
    {
        private String name;
        private String points;
        private static ArrayList<DataBaseHghstPnts> pointsList;

        public DataBaseHghstPnts(String name, String points)
        {
            this.name = name;
            this.points = points;
            pointsList = new ArrayList<DataBaseHghstPnts>();
        }

        public String getName()
        {
            return name;
        }

        public String getHighestPoints()
        {
            return points;
        }

        public ArrayList<DataBaseHghstPnts> getHghstPnts()
        {
            return pointsList;
        }

        public static void loadPoints()
        {
            String url = "https://www.landofbasketball.com/year_by_year_stats/2024_2025_most_points_rs.htm";

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
                        String name = cols.get(1).text();
                        String points = cols.get(3).text();

                        if (name.equals("Player") || points.equals("Points"))
                        {
                            continue;
                        }

                        pointsList.add(new DataBaseHghstPnts(name, points));
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


