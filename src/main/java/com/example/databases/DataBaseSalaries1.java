package com.example.databases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DataBaseSalaries1 extends Choice {

    private static ArrayList<Choice> salaries = new ArrayList<>();

    public DataBaseSalaries1(String name, double stat) {
        super(name, stat);
    }

    public static ArrayList<Choice> getSalaryList()
    {
        loadSalaries();
        return salaries;
    }

    public static void loadSalaries() {
        String url = "https://www.espn.com/nba/salaries/_/seasontype/3";

        try {
            Document doc = Jsoup.connect(url).get();
            Elements rows = doc.select("table tbody tr");

            for (Element row : rows) {
                Elements cols = row.select("td");

                if (cols.size() >= 4) {
                    String name = cols.get(1).text();
                    String salaryStr = cols.get(3).text().replaceAll("[$,]", "");

                    try {
                        double salary = Double.parseDouble(salaryStr);
                        salaries.add(new Choice(name, salary));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid salary: " + salaryStr);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}