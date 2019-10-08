package es.urjc.code.service;

import es.urjc.code.dto.MessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "swearing", url = "${swearing.microservice.url}")
public interface SwearingService {

    @RequestMapping(method = RequestMethod.POST, value = "/evaluate")
    boolean evaluateComment(MessageDto message);
}
