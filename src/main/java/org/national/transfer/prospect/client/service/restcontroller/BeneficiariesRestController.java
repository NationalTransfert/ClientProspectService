package org.national.transfer.prospect.client.service.restcontroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.national.transfer.prospect.client.service.model.ProspectClientBeneficiary;
import org.national.transfer.prospect.client.service.service.ProspectClientService;
import org.national.transfer.prospect.client.service.utils.LoggingUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CommonsLog
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/beneficiary")
public class BeneficiariesRestController {

    private final ProspectClientService clientService;

    @GetMapping("/{identityNumber}")
    public ResponseEntity<Set<ProspectClientBeneficiary>> getAllBeneficiariesByProspectClientId(@PathVariable String identityNumber) {
        log.info(LoggingUtils.getStartMessage(identityNumber));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getAllBeneficiariesByProspectClientId(identityNumber));
    }

    @PostMapping("/{identityNumber}")
    public ResponseEntity<ProspectClientBeneficiary> addBeneficiary(@PathVariable String identityNumber, @RequestBody ProspectClientBeneficiary beneficiary) {
        log.info(LoggingUtils.getStartMessage(identityNumber, beneficiary));
        ProspectClientBeneficiary prospectClientBeneficiary = clientService.addBeneficiary(identityNumber, beneficiary);
        log.debug(LoggingUtils.getEndMessage(prospectClientBeneficiary));
        return ResponseEntity.status(HttpStatus.CREATED).body(prospectClientBeneficiary);
    }

    @DeleteMapping("/{beneficiaryEmail}")
    public ResponseEntity<?> deleteBeneficiary(@PathVariable String beneficiaryEmail) {
        log.info(LoggingUtils.getStartMessage(beneficiaryEmail));
        clientService.deleteBeneficiary(beneficiaryEmail);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
