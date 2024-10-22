package com.hits.backend2.backend.data.clients.rates;

import com.hits.backend2.backend.data.clients.rates.config.RatesClientLimiterConfig;
import com.hits.backend2.integrations.model.CurrencyRates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "rates", url = "https://www.cbr-xml-daily.ru", configuration = RatesClientLimiterConfig.class)
public interface RatesClient {
    @GetMapping("/latest.json")
    CurrencyRates getRates();
}
