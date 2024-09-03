package com.ups.oop.controller;

import com.ups.oop.dto.BranchDTO;
import com.ups.oop.dto.ClientDTO;
import com.ups.oop.dto.WorkerDTO;
import com.ups.oop.service.BranchService;
import com.ups.oop.service.ClientService;
import com.ups.oop.service.WorkerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkerController {
    private  final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping("/worker")
    public ResponseEntity createWorker(@RequestBody WorkerDTO workerDTO){
        return this.workerService.createWorker(workerDTO);
    }

    @GetMapping("/get-all-worker")
    public ResponseEntity getAllWorker(){
        return this.workerService.getAllWorker();
    }

    @GetMapping("/get-worker")
    public ResponseEntity getWorkerById(@RequestParam String id){
        return this.workerService.getWorkerbyId(id);
    }

    @PutMapping("/update-worker")
    public ResponseEntity updateWorker(@RequestBody WorkerDTO workerDTO){
        return this.workerService.updateWorker(workerDTO);
    }

    @DeleteMapping("/remove-worker")
    public ResponseEntity deleteWorker(@RequestParam String id){
        return this.workerService.deleteWorkerById(id);
    }
}
