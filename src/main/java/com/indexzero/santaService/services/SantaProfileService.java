package com.indexzero.santaService.services;

import java.util.List;
import java.util.stream.Collectors;

import com.indexzero.santaService.model.SantaProfile;
import com.indexzero.santaService.repositories.SantaProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SantaProfileService {

    @Autowired
    private SantaProfileRepository santaProfileRepository;

    public List<SantaProfile> getSantas() {
        /* return santaProfileRepository.findAll(); */
        return convertDataFromList(santaProfileRepository.findAll());
    }
    /* Get available santas: */
    public List<SantaProfile> getAvailableSantas() {
        return convertDataFromList(santaProfileRepository.customFindAllAvailableSantas());
    }
    /* Availabel santas by postalcode */
    public List<SantaProfile> getAvailableSantasByPostalCode(String postalCode) {
        return convertDataFromList(santaProfileRepository.customFindAllAvailableSantasByPostalCode(postalCode));
    }
    /* Update profile */

    /* converts data only needed info*/
    private List<SantaProfile> convertDataFromList(List<SantaProfile> list) {
        return list.stream()
            .map(santa -> {
                SantaProfile santaProfile = new SantaProfile();
                santaProfile.setSantaProfileName("Testi: "+santa.getSantaProfileName());
                return santaProfile;
            }).collect(Collectors.toList());
    }
    
}
