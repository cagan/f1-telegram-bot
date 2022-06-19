package com.cagan.f1telegrambot.service;

import com.cagan.f1telegrambot.crawler.F1RacingCrawler;
import com.cagan.f1telegrambot.crawler.MotosportCrawler;
import com.cagan.f1telegrambot.dto.RacingStatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class F1StatusService {
    private final F1RacingCrawler crawlerService;

    @Autowired
    public F1StatusService(MotosportCrawler crawlerService) {
        this.crawlerService = crawlerService;
    }

    public List<RacingStatsResponse> getCurrentStats() {
        List<RacingStatsResponse> racingStatsResponses = new ArrayList<>();

        List<String> driverNames = crawlerService.getDriverNames();
        List<String> driverOrders = crawlerService.getPosition();
        List<String> driverNumbers = crawlerService.getDriverNumber();
        List<String> driverTeams = crawlerService.getDriverTeam();
        List<String> driverTimes = crawlerService.getDriverTime();

        for (int i = 0; i < driverNames.size(); i++) {
            racingStatsResponses.add(
                    new RacingStatsResponse(
                            driverNames.get(i),
                            Integer.valueOf(driverOrders.get(i)),
                            Integer.valueOf(driverNumbers.get(i)),
                            driverTeams.get(i),
                            driverTimes.get(i)
                    )
            );
        }

        return racingStatsResponses;
    }

    public String buildTableHeader() {
        StringBuilder html = new StringBuilder("<b>");
        html.append("Pos");
        html.append(appendSpace("Pos", 3));
        html.append("Driver");
        html.append(appendSpace("Driver", 31));
//        html.append("Team");
//        html.append(appendSpace("Team", 25));
//        html.append("<pre>-------------------------------------</pre>");
        html.append("</b>");
        return html.toString();
    }

    public String getStatusHtml() {
        StringBuilder html = new StringBuilder();
        html.append(buildTableHeader());
        List<RacingStatsResponse> statList = getCurrentStats();

        for (RacingStatsResponse response : statList) {
            String positionSpace = appendSpace(response.getPosition().toString(), 2);
//            String driverSpace = appendSpace(response.getDriverName(), 16);

            html.append("<pre>")
                    .append(response.getPosition())
                    .append(positionSpace)
                    .append(response.getDriverName())
//                    .append(driverSpace)
//                    .append(response.getTeam())
                    .append("</pre>");
        }

        return html.toString();
    }

    public String appendSpace(String str, int totalSpaceCount) {
        StringBuilder space = new StringBuilder();
        for (int i = 0; i < totalSpaceCount - str.length(); i++) {
            space.append(" ");
        }

        space.append(" |");
        return space.toString();
    }
}
