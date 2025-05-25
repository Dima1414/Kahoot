package com.example.databases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class DataBaseHghstPnts {
    private static ArrayList<Choice> pointsList = new ArrayList<>();

    public DataBaseHghstPnts() {
        pointsList = new ArrayList<>();
    }


    public ArrayList<Choice> getHghstPnts() {
        loadPoints();
        Collections.shuffle(pointsList);
        return pointsList;
    }

    public static void loadPoints() {
        String url = "https://www.basketball-reference.com/leaders/pts_game.html";
        HashSet<String> seenNames = new HashSet<>();  // Track unique names

        try {
            Document doc = Jsoup.connect(url).get();
            Elements rows = doc.select("table tbody tr");

            for (Element row : rows) {
                Elements cols = row.select("td");

                if (cols.size() > 3) {
                    String name = cols.get(1).text().replace("*", "").trim(); // Remove asterisks
                    String points = cols.get(2).text();

                    if (name.equals("Player") || points.equals("Points") ||
                            points.equals("W/L Game") || points.trim().isEmpty()) {
                        continue;
                    }

                    if (seenNames.contains(name)) continue;
                    seenNames.add(name);

                    try {
                        points = points.replaceAll("[^0-9.]", "");
                        double pointsValue = Double.parseDouble(points);
                        pointsList.add(new Choice(name, pointsValue));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid points value for player: " + name);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading points data. Using default data.");
        }
    }
}

