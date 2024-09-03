package com.ups.oop.controller;

import com.ups.oop.dto.BranchDTO;
import com.ups.oop.dto.ClientDTO;
import com.ups.oop.service.BranchService;
import com.ups.oop.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    private  final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/client")
    public ResponseEntity createClient(@RequestBody ClientDTO clientDTO){
        return this.clientService.createClient(clientDTO);
    }

    @GetMapping("/get-all-client")
    public ResponseEntity getAllClient(){
        return this.clientService.getAllClient();
    }

    @GetMapping("/get-client")
    public ResponseEntity getClientById(@RequestParam String id){
        return this.clientService.getClientById(id);
    }

    @PutMapping("/update-client")
    public ResponseEntity updateClient(@RequestBody ClientDTO clientDTO){
        return this.clientService.updateClient(clientDTO);
    }

    @DeleteMapping("/remove-client")
    public ResponseEntity deleteClient(@RequestParam String id){
        return this.clientService.deleteClientById(id);
    }
}
