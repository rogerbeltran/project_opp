package com.ups.oop.controller;


import com.ups.oop.dto.BranchDTO;
import com.ups.oop.service.BranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BranchController {
private  final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/branch")
    public ResponseEntity createBranch(@RequestBody BranchDTO branchDTO){
        return this.branchService.createBranch(branchDTO);
    }

    @GetMapping("/get-all-branches")
    public ResponseEntity getAllBranches(){
        return this.branchService.getAllBranch();
    }

    @GetMapping("/get-branch")
    public ResponseEntity getBranchById(@RequestParam String id){
        return this.branchService.getBranchbyId(id);
    }

    @PutMapping("/update-branch")
    public ResponseEntity updateBranch(@RequestBody BranchDTO branchDTO){
        return this.branchService.updateBranch(branchDTO);
    }

    @DeleteMapping("/remove-branch")
    public ResponseEntity deleteBranch(@RequestParam String id){
        return this.branchService.deleteBranchById(id);
    }
}
