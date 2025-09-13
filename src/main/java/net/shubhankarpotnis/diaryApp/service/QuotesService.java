package net.shubhankarpotnis.diaryApp.service;

import net.shubhankarpotnis.diaryApp.apiResponse.QuoteApiResponse;
import net.shubhankarpotnis.diaryApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuotesService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public QuoteApiResponse getQuote() {
        return restTemplate.getForObject(appCache.APP_CACHE.get("quotes_api"), QuoteApiResponse.class);
    }

}
