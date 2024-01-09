package org.national.transfer.prospect.client.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.national.transfer.prospect.client.service.exception.ProspectClientNotFoundException;
import org.national.transfer.prospect.client.service.model.ProspectClientBeneficiary;
import org.national.transfer.prospect.client.service.model.ProspectClientDetails;
import org.national.transfer.prospect.client.service.rep.ProspectClientBeneficiaryRepository;
import org.national.transfer.prospect.client.service.rep.ProspectClientDetailsRepository;
import org.national.transfer.prospect.client.service.utils.LoggingUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
@CommonsLog
@RequiredArgsConstructor
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProspectClientService {

    private final ProspectClientDetailsRepository prospectClientDetailsRepository;
    private final ProspectClientBeneficiaryRepository prospectClientBeneficiaryRepository;

    public ProspectClientDetails addProspectClient(ProspectClientDetails prospectClientDetails) {

        log.info(LoggingUtils.getStartMessage(prospectClientDetails));

        ProspectClientDetails prospect = new ProspectClientDetails(prospectClientDetails.getIdentityNumber(), prospectClientDetails.getLastname(),
                prospectClientDetails.getFirstname(),
                prospectClientDetails.getEmail(), prospectClientDetails.getGender(), prospectClientDetails.getIdentityType(),
                prospectClientDetails.getBirthdayDate(), prospectClientDetails.getOccupation(), prospectClientDetails.getAddress(),
                prospectClientDetails.getCity(), prospectClientDetails.getPhoneNumber(),
                prospectClientDetails.getNationality(), prospectClientDetails.getCountry(), prospectClientDetails.getEmissionCountry());

        log.debug(LoggingUtils.getEndMessage(prospect));
        return prospectClientDetailsRepository.save(prospect);
    }

    public ProspectClientBeneficiary addBeneficiary(String identityNumber, ProspectClientBeneficiary beneficiary) {
        log.info(LoggingUtils.getMessage(identityNumber, beneficiary));
        return prospectClientDetailsRepository.findById(identityNumber).map(prospect -> {
            beneficiary.setProspectClientDetails(prospect);
            return prospectClientBeneficiaryRepository.save(beneficiary);
        }).orElseThrow(() -> new ProspectClientNotFoundException("PROSPECT_NOT_FOUND"));
    }

    public ProspectClientDetails getProspect(String identityNumber) {
        log.info(LoggingUtils.getMessage(identityNumber));
        return prospectClientDetailsRepository.findById(identityNumber).orElseThrow(() -> new ProspectClientNotFoundException("PROSPECT_NOT_FOUND"));
    }

    public Set<ProspectClientBeneficiary> getAllBeneficiariesByProspectClientId(String identityNumber) {
        log.info(LoggingUtils.getStartMessage(identityNumber));
        List<ProspectClientBeneficiary> list = prospectClientBeneficiaryRepository.findAll();
        return list.stream().filter(beneficiary -> beneficiary.getProspectClientDetails().getIdentityNumber().equals(identityNumber)
        ).collect(Collectors.toSet());
    }


    public void deleteBeneficiary(String beneficiaryEmail) {
        ProspectClientBeneficiary prospectClientBeneficiary = prospectClientBeneficiaryRepository.findById(beneficiaryEmail).get();
        if (ObjectUtils.isEmpty(prospectClientBeneficiary)) {
            throw new ProspectClientNotFoundException("BENEFICIARY_NOT_FOUND");
        }

        prospectClientBeneficiaryRepository.deleteById(beneficiaryEmail);
    }
}
