package com.vobi.bank.mapper;

import com.vobi.bank.dto.CustomerDTO;
import com.vobi.bank.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Mapping(source = "documentType.dotyId", target = "dotyId")
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mapping(source="dotyId", target = "documentType.dotyId")
    Customer customerDTOtoCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> customerListToCustomerDTOList(List<Customer> customerList);

    List<Customer> customerDTOListToCustomerList(List<CustomerDTO> customerDTOList);
}
