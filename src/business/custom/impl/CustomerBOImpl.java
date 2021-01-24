package business.custom.impl;

import business.custom.CustomerBO;
import dao.DAOFactory;
import dao.DAOTypes;
import dao.custom.CustomerDAO;
//import db.HibernateUtil;
import dto.CustomerDTO;
import entity.Customer;
import org.hibernate.Session;
import util.FactoryConfiguration;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerBOImpl implements CustomerBO {

    private CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);

//    public CustomerBOImpl(){
//        String dao = DAOFactory.getInstance().<String>getDAO(DAOTypes.CUSTOMER);
//    }

    @Override
    public CustomerDTO getCustomerById(String id) throws Exception {
        try(Session session =  FactoryConfiguration.getInstance().getSession()){
            session.beginTransaction();
            customerDAO.setSession(session);
            Customer customer = customerDAO.find(id);
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(),customer.getSalary());
            session.getTransaction().commit();
            return customerDTO;
        }
    }

    @Override
    public String newCustomerID() throws Exception {
        String lastID = customerDAO.getLastCustomerID();

        int newID = Integer.parseInt(lastID.substring(1, 4)) + 1;

        if(newID < 10){
            return "C00"+newID;
        }else if(newID < 100){
            return "C0"+newID;
        }else{
            return "C"+newID;
        }
    }

    public List<CustomerDTO> getAllCustomers() throws Exception {
        try(Session session = FactoryConfiguration.getInstance().getSession()){
            session.beginTransaction();
            customerDAO.setSession(session);
            List<CustomerDTO> collect = customerDAO.findAll().stream().map(customer -> new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(),customer.getSalary())).collect(Collectors.toList());
            session.getTransaction().commit();
            return collect;
        }
    }

    public boolean saveCustomer(CustomerDTO dto) throws Exception {
        try(Session session =  FactoryConfiguration.getInstance().getSession()){
            session.beginTransaction();
            customerDAO.setSession(session);
            customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getAddress(),dto.getSalary()));
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            throw e;
        }
    }

    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        try(Session session =  FactoryConfiguration.getInstance().getSession()){
            session.beginTransaction();
            customerDAO.setSession(session);
            customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getAddress(),dto.getSalary()));
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            throw e;
        }
    }

    public boolean removeCustomer(String id) throws Exception {
        try(Session session =  FactoryConfiguration.getInstance().getSession()){
            session.beginTransaction();
            customerDAO.setSession(session);
            customerDAO.delete(id);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            throw e;
        }
    }

}
