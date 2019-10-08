package es.urjc.code.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SwearingServiceImpl implements SwearingService {

    private static final List<String> INSULT_LIST = Arrays.asList("lechuguino", "gilipipas", "botarate", "zascandil",
            "adoquin", "batracio", "malandrin");

    @Override
    public boolean checkComment(String comment) {

        return INSULT_LIST
                .stream()
                .anyMatch(comment::contains);
    }
}
