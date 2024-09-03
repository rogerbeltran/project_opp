package com.ups.oop.service;

import com.ups.oop.dto.ClientDTO;
import com.ups.oop.dto.PersonDTO;
import com.ups.oop.entity.Client;
import com.ups.oop.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private List<ClientDTO> clientDTOList = new ArrayList<>();

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ResponseEntity createClient(ClientDTO clientDTO) {
        String clientId = clientDTO.getId();
        //check repository if record exist
        Optional<Client> clientOptional = clientRepository.findById(Long.valueOf(clientId));
        if(clientOptional.isPresent()) {
            String errorMessage = "Customer with id " + clientId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            //Before Register Person, name and lastname are present
            if(clientDTO.getName().contains(" ")) {
                //Build Person and save in Repository
                Client clientRecord = new Client();
                clientRecord.setClientCode(clientId);
                String[] nameStrings = clientDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                clientRecord.setName(name);
                clientRecord.setLastName(lastname);
                clientRecord.setAge(clientDTO.getAge());
                clientRepository.save(clientRecord);
                return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person name must contain two strings separated by a whitespace");
            }
        }
    }

    public ResponseEntity getAllClient() {
        List<ClientDTO> clientList = getClient();
        if(clientList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientList);
    }

    public List<ClientDTO> getClient() {
        Iterable<Client> personIterable = clientRepository.findAll();
        List<ClientDTO> clientList = new ArrayList<>();
        for(Client cus : personIterable) {
            ClientDTO client = new ClientDTO();
            client.setId(cus.getPersonId());
            client.setName(cus.getName() + "-" + cus.getLastName());
            client.setAge(cus.getAge());
            clientList.add(client);
        }
        return clientList;
    }

    public ResponseEntity getClientById(String clientId) {
        Optional<Client> clientOptional = clientRepository.findById(Long.valueOf(clientId));
        if(clientOptional.isPresent()) {
            //if record was found
            Client clientFound = clientOptional.get();
            ClientDTO customer = new ClientDTO(clientFound.getPersonId(),
                    clientFound.getName() + "-" + clientFound.getLastName(),
                    clientFound.getAge(), clientFound.getClientCode());
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } else {
            //if record wasn't found
            String errorMessage = "Person with id " + clientId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity updateClient(ClientDTO clientDTO) {
        String requestId = clientDTO.getId();
        //check repository if record exist
        Optional<Client> clientOptional = clientRepository.findById(Long.valueOf(requestId));
        if(clientOptional.isPresent()) {
            //If record exists, then perform Update
            Client customer = clientOptional.get();
            if(clientDTO.getName().contains(" ")) {
                //Build Person and save in Repository
                customer.setPersonId(requestId);
                String[] nameStrings = clientDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                customer.setName(name);
                customer.setLastName(lastname);
                customer.setAge(clientDTO.getAge());
                clientRepository.save(customer);
                return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person name must contain two strings separated by a whitespace");
            }
        } else {
            String errorMessage = "Customer with id " + requestId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity deleteClientById(String id) {
        String message = "Customer with id " + id;
        Optional<Client> clientOptional = clientRepository.findById(Long.valueOf(id));
        if(clientOptional.isPresent()) {
            //If record was found, then delete record
            clientRepository.delete(clientOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
        } else {
            //Return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
        }
    }
}