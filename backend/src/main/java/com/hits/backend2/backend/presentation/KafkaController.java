package com.hits.backend2.backend.presentation;

import com.hits.backend2.crutch.api.KafkaApi;
import com.hits.backend2.crutch.api.KafkaApiDelegate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaController implements KafkaApi {
    private final KafkaApiDelegate delegate;

    @Override
    public KafkaApiDelegate getDelegate() {
        return delegate;
    }
}
