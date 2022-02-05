package com.rezso.backend.controller;

import com.rezso.backend.model.CRM;
import com.rezso.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/crm")
@CrossOrigin(origins = "http://localhost:3000")
public class CRMController {

    private final UserRepository userRepository;

    public CRMController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<CRM> all() {
        return userRepository.findAll();
    }

    @GetMapping("/customer/{id}")
    public Optional<CRM> getUser(@PathVariable Integer id){
        return userRepository.findById(id);
    }

    @PostMapping("/customer")
    public CRM newUser(@RequestBody CRM newUser) {
        return userRepository.save(newUser);
    }

    @PutMapping("/customer")
    public CRM putUser(@RequestBody CRM newUser){
        Integer id = newUser.getId();
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setCity(newUser.getCity());
                    user.setAddress(newUser.getAddress());
                    user.setEmail(newUser.getEmail());
                    user.setFax(newUser.getFax());
                    user.setLanguage(newUser.getLanguage());
                    user.setMobile(newUser.getMobile());
                    user.setPhone(newUser.getPhone());
                    user.setWebsite(newUser.getWebsite());
                    user.setState(newUser.getState());
                    user.setZipCode(newUser.getZipCode());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    @DeleteMapping("/customer/{id}")
    public String deleteUser(@PathVariable Integer id){
        userRepository.deleteById(id);
        return "User Deleted";
    }


}
