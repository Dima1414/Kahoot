package com.example.KahootProject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DataBaseSalaries
{
    private String name;
    private String salary;
    public static ArrayList<DataBaseSalaries> salaries;

    public DataBaseSalaries(String name, String salary)
    {
        this.name = name;
        this.salary = salary;
        salaries = new ArrayList<DataBaseSalaries>();
    }

    public String getName()
    {
        return name;
    }

    public String getSalary()
    {
        return salary;
    }

    public ArrayList<DataBaseSalaries> getSalaries()
    {
        return salaries;
    }

    public static void loadSalaries()
    {
        String url = "https://www.espn.com/nba/salaries/_/seasontype/3";

        try
        {
            Document document = Jsoup.connect(url).get();
            Elements rows = document.select("table tbody tr");

            for (int i = 0; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cols = row.select("td");

                if (cols.size() >= 4)
                {
                    String name = cols.get(1).text();
                    String salary = cols.get(3).text();

                    if (name.equalsIgnoreCase("NAME") && salary.equalsIgnoreCase("SALARY"))
                    {
                        continue;
                    }

                    salaries.add(new DataBaseSalaries(name, salary));
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
