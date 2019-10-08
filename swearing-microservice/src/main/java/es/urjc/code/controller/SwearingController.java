package es.urjc.code.controller;

import es.urjc.code.dto.MessageDto;
import es.urjc.code.service.SwearingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwearingController {

    private SwearingService swearingService;

    @Autowired
    public SwearingController(SwearingService swearingService) {
        this.swearingService = swearingService;
    }

    @PostMapping(value = "/evaluate")
    public Boolean evaluateComment(@RequestBody MessageDto messageDto) {

        return swearingService.checkComment(messageDto.getMessage().toLowerCase());
    }
}
