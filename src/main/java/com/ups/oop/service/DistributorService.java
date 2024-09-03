package com.ups.oop.service;

import com.ups.oop.dto.DistributorDTO;
import com.ups.oop.entity.Distributor;
import com.ups.oop.repository.DistributorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DistributorService {
    private final DistributorRepository distributorRepository;
    private List<DistributorDTO> distributorDTOList = new ArrayList<>();

    public DistributorService(DistributorRepository distributorRepository, List<DistributorDTO> distributorDTOList) {
        this.distributorRepository = distributorRepository;
        this.distributorDTOList = distributorDTOList;
    }

    public ResponseEntity createDistributor(DistributorDTO distributorDTO) {
        String distributorId = distributorDTO.getId();
        //check repository if record exist
        Optional<Distributor> distributorOptional = distributorRepository.findById(Long.valueOf(distributorId));
        if(distributorOptional.isPresent()) {
            String errorMessage = "Supplier with id " + distributorId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            //Before Register Person, name and lastname are present
            if(distributorDTO.getDistributor_name().contains(" ")) {
                //Build Person and save in Repository
                Distributor supplierRecord = new Distributor();
                supplierRecord.setId(supplierRecord.getId());
                String[] nameStrings = distributorDTO.getDistributor_name().split(" ");
                String name = nameStrings[0];
                supplierRecord.setName(name);
                distributorRepository.save(supplierRecord);
                return ResponseEntity.status(HttpStatus.OK).body(distributorDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name must contain two strings separated by a whitespace");
            }
        }
    }

    public ResponseEntity getAllDistributor() {
        List<DistributorDTO> distributorList = getDistributor();
        if(distributorList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supplier List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(distributorList);
    }

    public List<DistributorDTO> getDistributor() {
        Iterable<Distributor> supplierIterable = distributorRepository.findAll();
        List<DistributorDTO> supplierList = new ArrayList<>();
        for(Distributor distri : supplierIterable) {
            DistributorDTO dist = new DistributorDTO();
            dist.setId(String.valueOf(distri.getId()));
            dist.setDistributor_name(distri.getName());
            supplierList.add(dist);
        }
        return supplierList;
    }

    public ResponseEntity getDistributorbyId(String supplierId) {
        Optional<Distributor> distributorOptional = distributorRepository.findById(Long.valueOf(supplierId));
        if(distributorOptional.isPresent()) {
            //if record was found
            Distributor supplierFound = distributorOptional.get();
            DistributorDTO Supplier = new DistributorDTO(String.valueOf(supplierFound.getId()), supplierFound.getName());
            return ResponseEntity.status(HttpStatus.OK).body(Supplier);
        } else {
            //if record wasn't found
            String errorMessage = "Supplier with id " + supplierId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity updateDistributor(DistributorDTO distributorDTO) {
        String distributorId = distributorDTO.getId();
        //check repository if record exist
        Optional<Distributor> distributorOptional = distributorRepository.findById(Long.valueOf(distributorId));
        if(distributorOptional.isPresent()) {
            //If record exists, then perform Update
            Distributor distributor = distributorOptional.get();
            if(distributorDTO.getId().contains(" ")) {
                //Build Person and save in Repository
                distributor.setId(Long.valueOf(distributorId));
                String[] nameStrings = distributorDTO.getDistributor_name().split(" ");
                String name = nameStrings[0];
                distributor.setName(name);
                distributorRepository.save(distributor);
                return ResponseEntity.status(HttpStatus.OK).body(distributorDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Store name must contain two strings separated by a whitespace");
            }
        } else {
            String errorMessage = "Store with id " + distributorId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity deleteDistributorById(String id) {
        String message = "Supplier with id " + id;
        Optional<Distributor> distributorOptional = distributorRepository.findById(Long.valueOf(id));
        if(distributorOptional.isPresent()) {
            //If record was found, then delete record
            distributorRepository.delete(distributorOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
        } else {
            //Return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
        }
    }
}