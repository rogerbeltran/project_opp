package com.ups.oop.service;

import com.ups.oop.dto.DetailsDTO;
import com.ups.oop.entity.Details;
import com.ups.oop.repository.DetailsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailsService {
    private final DetailsRepository detailsRepository;

    public DetailsService(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    public List<DetailsDTO> getDetails() {
        Iterable<Details> detailsIterable = detailsRepository.findAll();
        List<DetailsDTO> detailsList = new ArrayList<>();
        for (Details detail : detailsIterable) {
            DetailsDTO detailsDTO = new DetailsDTO();
            detailsDTO.setId(detail.getId().toString());
            detailsDTO.setReceipt(detail.getReceipt().getSerial());
            detailsDTO.setProduct(detail.getProduct().getName());
            detailsDTO.setUnit_price((int) detail.getProduct().getPrice());
            detailsDTO.setQuantity(detail.getQuantity());
            detailsList.add(detailsDTO);
        }
        return detailsList;
    }
}
