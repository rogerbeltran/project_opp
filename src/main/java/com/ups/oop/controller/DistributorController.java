package com.ups.oop.controller;

import com.ups.oop.dto.BranchDTO;
import com.ups.oop.dto.ClientDTO;
import com.ups.oop.dto.DistributorDTO;
import com.ups.oop.service.BranchService;
import com.ups.oop.service.ClientService;
import com.ups.oop.service.DistributorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DistributorController {
    private  final DistributorService distributorService;

    public DistributorController(DistributorService distributorService) {
        this.distributorService = distributorService;
    }

    @PostMapping("/distributor")
    public ResponseEntity createDistributor(@RequestBody DistributorDTO distributorDTO){
        return this.distributorService.createDistributor(distributorDTO);
    }

    @GetMapping("/get-all-distributor")
    public ResponseEntity getAllDistributor(){
        return this.distributorService.getAllDistributor();
    }

    @GetMapping("/get-distributor")
    public ResponseEntity getDistributorById(@RequestParam String id){
        return this.distributorService.getDistributorbyId(id);
    }

    @PutMapping("/update-distributor")
    public ResponseEntity updateDistributor(@RequestBody DistributorDTO distributorDTO){
        return this.distributorService.updateDistributor(distributorDTO);
    }

    @DeleteMapping("/remove-distributor")
    public ResponseEntity deleteDistributor(@RequestParam String id){
        return this.distributorService.deleteDistributorById(id);
    }
}
