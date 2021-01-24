package dao.custom;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.Customer;
import org.hibernate.query.NativeQuery;

import javax.persistence.EntityManager;

public interface CustomerDAO extends CrudDAO<Customer,String> {

    NativeQuery<Integer> count() throws Exception;
    public String getLastCustomerID()throws Exception;

}
