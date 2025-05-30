package com.example.databases;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DataBaseSalaries {

    private static ArrayList<Choice> salaries = new ArrayList<>();

    public DataBaseSalaries() {
        salaries = new ArrayList<>();
    }

    public ArrayList<Choice> getSalaryList()
    {
        loadSalaries();
        return salaries;
    }

    public static void loadSalaries() {
        String url = "https://www.espn.com/nba/salaries/_/seasontype/3";

        try {
            Document doc = Jsoup.connect(url).get();
            Elements rows = doc.select("table tbody tr");

            for (int i = 0; i < rows.size(); i++) {
                Element row = rows.get(i);
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
