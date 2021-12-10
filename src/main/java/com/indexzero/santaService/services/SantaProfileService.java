package com.indexzero.santaService.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.indexzero.santaService.model.SantaProfile;
import com.indexzero.santaService.model.UserAccount;
import com.indexzero.santaService.repositories.SantaProfileRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SantaProfileService {

    @Autowired
    private SantaProfileRepository santaProfileRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Transactional
    public void saveSantaProfile(SantaProfile santaProfile) {
        santaProfileRepository.saveAndFlush(santaProfile);
    }
    public Optional<SantaProfile> getProfileByid(Long id) {
        return santaProfileRepository.findById(id);
    }

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
    @Transactional
    public void updateSantaProfileInfo(
        UserAccount userAccount, 
        SantaProfile existingSantaProfile,
        SantaProfile updatedSantaProfile) {

        /*Santaprofile should not be empty:  */
        if (!updatedSantaProfile.getSantaProfileName().isBlank()) {
            existingSantaProfile.setSantaProfileName(updatedSantaProfile.getSantaProfileName());
        }
        existingSantaProfile.setInfo(updatedSantaProfile.getInfo());
        existingSantaProfile.setPrice(updatedSantaProfile.getPrice());
        existingSantaProfile.setAvailable(updatedSantaProfile.isAvailable());
        /* santaProfileRepository.save(existingSantaProfile); */
        

    }
    /* Delete Santaprofile */
    public void deleteSantaprofile(SantaProfile santaprofile) {
        santaProfileRepository.delete(santaprofile);
    }

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
