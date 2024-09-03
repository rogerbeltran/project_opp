package com.ups.oop.service;

import com.ups.oop.dto.WorkerDTO;
import com.ups.oop.entity.Worker;
import com.ups.oop.repository.WorkerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;
    private List<WorkerDTO> workerDTOList = new ArrayList<>();

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public ResponseEntity createWorker(WorkerDTO workerDTO) {
        String workerId = workerDTO.getId();
        //check repository if record exist
        Optional<Worker> workerOptional = workerRepository.findById(Long.valueOf(workerId));
        if(workerOptional.isPresent()) {
            String errorMessage = "Worker with id " + workerId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            //Before Register Person, name and lastname are present
            if(workerDTO.getName().contains(" ")) {
                //Build Person and save in Repository
                Worker workerRecord = new Worker();
                workerRecord.setWorkerCode(workerId);
                String[] nameStrings = workerDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                workerRecord.setName(name);
                workerRecord.setLastName(lastname);
                workerRecord.setAge(workerDTO.getAge());
                workerRepository.save(workerRecord);
                return ResponseEntity.status(HttpStatus.OK).body(workerDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee name must contain two strings separated by a whitespace");
            }
        }
    }

    public ResponseEntity getAllWorker() {
        List<WorkerDTO> workerList = getWorker();
        if(workerList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(workerList);
    }

    public List<WorkerDTO> getWorker() {
        Iterable<Worker> personIterable = workerRepository.findAll();
        List<WorkerDTO> workerList = new ArrayList<>();
        for(Worker wor : personIterable) {
            WorkerDTO worker = new WorkerDTO();
            worker.setId(wor.getPersonId());
            worker.setName(wor.getName() + "-" + wor.getLastName());
            worker.setAge(wor.getAge());
            workerList.add(worker);
        }
        return workerList;
    }

    public ResponseEntity getWorkerbyId(String employeeId) {
        Optional<Worker> employeeOptional = workerRepository.findById(Long.valueOf(employeeId));
        if(employeeOptional.isPresent()) {
            //if record was found
            Worker employeeFound = employeeOptional.get();
            WorkerDTO customer = new WorkerDTO(employeeFound.getPersonId(),
                    employeeFound.getName() + "-" + employeeFound.getLastName(),
                    employeeFound.getAge(), employeeFound.getWorkerCode());
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } else {
            //if record wasn't found
            String errorMessage = "Employee with id " + employeeId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity updateWorker(WorkerDTO employeeDTO) {
        String workerId = employeeDTO.getId();
        //check repository if record exist
        Optional<Worker> workerOptional = workerRepository.findById(Long.valueOf(workerId));
        if(workerOptional.isPresent()) {
            //If record exists, then perform Update
            Worker worker = workerOptional.get();
            if(employeeDTO.getName().contains(" ")) {
                //Build Person and save in Repository
                worker.setPersonId(workerId);
                String[] nameStrings = employeeDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                worker.setName(name);
                worker.setLastName(lastname);
                worker.setAge(employeeDTO.getAge());
                workerRepository.save(worker);
                return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee name must contain two strings separated by a whitespace");
            }
        } else {
            String errorMessage = "Worker with id " + workerId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity deleteWorkerById(String id) {
        String message = "Employee with id " + id;
        Optional<Worker> employeeOptional = workerRepository.findById(Long.valueOf(id));
        if(employeeOptional.isPresent()) {
            //If record was found, then delete record
            workerRepository.delete(employeeOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
        } else {
            //Return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
        }
    }
}