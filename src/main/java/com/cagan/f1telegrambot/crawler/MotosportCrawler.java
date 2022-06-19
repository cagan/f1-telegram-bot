package com.cagan.f1telegrambot.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MotosportCrawler implements F1RacingCrawler {
    private static final Logger log = LoggerFactory.getLogger(MotosportCrawler.class);
    private static final String URL = "https://tr.motorsport.com/f1/results/2022";
    private final Element results;

    public MotosportCrawler() {
        results = getResultsElement();
    }

    public Element getResultsElement() {
        Document doc = null;

        try {
            doc = Jsoup.connect(URL).get();
        } catch (Exception exception) {
            log.info("Can not connect results [PAGE: {}]", URL);
        }
        assert doc != null;

        return doc.select(".ms-table--result tbody").get(0);
    }

    @Override
    public List<String> getDriverNames() {
        Elements driverNameElements = results.getElementsByClass("name");
        return makeTextList(driverNameElements);
    }

    @Override
    public List<String> getDriverNumber() {
        Elements driverNumberElements = results.getElementsByClass("ms-table_field--number");
        return makeTextList(driverNumberElements);
    }

    @Override
    public List<String> getPosition() {
        Elements positionElement = results.getElementsByClass("ms-table_field--pos");
        return makeTextList(positionElement);
    }

    @Override
    public List<String> getDriverTeam() {
        Elements driverTeamElements = results.getElementsByClass("ms-table_field--car_make");
        return makeTextList(driverTeamElements);
    }

    @Override
    public List<String> getDriverTime() {
        Elements driverTime = results.getElementsByClass("ms-table_field--time");
        return makeTextList(driverTime);
    }

    private List<String> makeTextList(Elements elements) {
        List<String> list = new ArrayList<>();
        for (Element e : elements) {
            list.add(e.text());
        }
        return list;
    }
}
