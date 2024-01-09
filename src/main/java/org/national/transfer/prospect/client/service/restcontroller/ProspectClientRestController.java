package org.national.transfer.prospect.client.service.restcontroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.national.transfer.prospect.client.service.model.ProspectClientDetails;
import org.national.transfer.prospect.client.service.service.ProspectClientService;
import org.national.transfer.prospect.client.service.utils.LoggingUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CommonsLog
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/prospect")
public class ProspectClientRestController {

    private final ProspectClientService prospectClientService;

    @PostMapping
    public ResponseEntity<ProspectClientDetails> addProspectClient(@RequestBody ProspectClientDetails prospectClientDetails) {
        log.info(LoggingUtils.getStartMessage(prospectClientDetails));
        ProspectClientDetails prospect = prospectClientService.addProspectClient(prospectClientDetails);
        log.debug(LoggingUtils.getEndMessage(prospect));
        return ResponseEntity.status(HttpStatus.CREATED).body(prospect);
    }

    @GetMapping("/{identityNumber}")
    public ResponseEntity<ProspectClientDetails> getProspect(@PathVariable String identityNumber) {
        log.info(LoggingUtils.getStartMessage(identityNumber));
        ProspectClientDetails prospect = prospectClientService.getProspect(identityNumber);
        log.debug(LoggingUtils.getEndMessage(prospect));
        return ResponseEntity.status(HttpStatus.OK).body(prospect);
    }
}
