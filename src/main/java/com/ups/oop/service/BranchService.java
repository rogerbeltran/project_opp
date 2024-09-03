package com.ups.oop.service;

import com.ups.oop.entity.Branch;
import com.ups.oop.dto.BranchDTO;
import com.ups.oop.repository.BranchRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    private final BranchRepository branchRepository;
    private List<BranchDTO> branchDTOList = new ArrayList<>();

    public BranchService(BranchRepository branchRepository, List<BranchDTO> branchDTOList) {
        this.branchRepository = branchRepository;
        this.branchDTOList = branchDTOList;
    }

    public ResponseEntity createBranch(BranchDTO branchDTO) {
        String branchId = branchDTO.getId();
        //check repository if record exist
        Optional<Branch> branchOptional = branchRepository.findById(Long.valueOf(branchId));
        if(branchOptional.isPresent()) {
            String errorMessage = "Branch with id " + branchId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            //Before Register Person, name and lastname are present
            if(branchDTO.getBranch().contains(" ")) {
                //Build Person and save in Repository
                Branch branchRecord = new Branch();
                branchRecord.setId(branchRecord.getId());
                String[] nameStrings = branchDTO.getStore().split(" ");
                String[] nameStrings2 = branchDTO.getBranch().split(" ");
                String name = nameStrings[0];
                branchRecord.setBranch_name(name);
                branchRepository.save(branchRecord);
                return ResponseEntity.status(HttpStatus.OK).body(branchDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name must contain two strings separated by a whitespace");
            }
        }
    }

    public ResponseEntity getAllBranch() {
        List<BranchDTO> branchList = getBranch();
        if(branchList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(branchList);
    }

    public List<BranchDTO> getBranch() {
        Iterable<Branch> branchIterable = branchRepository.findAll();
        List<BranchDTO> branchList = new ArrayList<>();
        for(Branch bra : branchIterable) {
            BranchDTO branch = new BranchDTO();
            branch.setId(String.valueOf(bra.getId()));
            branch.setStore(toString());
            branch.setBranch(String.valueOf(bra.getBranch_name()));

            branchList.add(branch);
        }
        return branchList;
    }

    public ResponseEntity getBranchbyId(String branchId) {
        Optional<Branch> branchOptional = branchRepository.findById(Long.valueOf(branchId));
        if(branchOptional.isPresent()) {
            //if record was found
            Branch branchFound = branchOptional.get();
            BranchDTO Store = new BranchDTO(String.valueOf(branchFound.getId()), branchFound.getBranch_name(), branchFound.getBranch_name());
            return ResponseEntity.status(HttpStatus.OK).body(Store);
        } else {
            //if record wasn't found
            String errorMessage = "Store with id " + branchId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity updateBranch(BranchDTO branchDTO) {
        String branchId = branchDTO.getId();
        //check repository if record exist
        Optional<Branch> storeOptional = branchRepository.findById(Long.valueOf(branchId));
        if(storeOptional.isPresent()) {
            //If record exists, then perform Update
            Branch store = storeOptional.get();
            if(branchDTO.getStore().contains(" ")) {
                //Build Person and save in Repository
                store.setId(Long.valueOf(branchId));
                String[] nameStrings = branchDTO.getStore().split(" ");
                String name = nameStrings[0];
                store.setBranch_name(name);
                branchRepository.save(store);
                return ResponseEntity.status(HttpStatus.OK).body(branchDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Store name must contain two strings separated by a whitespace");
            }
        } else {
            String errorMessage = "Store with id " + branchId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity deleteBranchById(String id) {
        String message = "Product with id " + id;
        Optional<Branch> productOptional = branchRepository.findById(Long.valueOf(id));
        if(productOptional.isPresent()) {
            //If record was found, then delete record
            branchRepository.delete(productOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
        } else {
            //Return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
        }
    }
}