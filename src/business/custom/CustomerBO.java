package business.custom;

import business.SuperBO;
import dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {

    public List<CustomerDTO> getAllCustomers() throws Exception;

    public boolean saveCustomer(CustomerDTO dto)  throws Exception;

    public boolean updateCustomer(CustomerDTO dto)throws Exception;

    public boolean removeCustomer(String id)throws Exception;

    public CustomerDTO getCustomerById(String id)throws Exception;

    public String newCustomerID()throws Exception;

}
