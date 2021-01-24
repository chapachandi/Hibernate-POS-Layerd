package dao.custom.impl;

import dao.CrudDAOimpl;
import dao.custom.OrderDAO;
import entity.Order;

public class OrderDAOImpl extends CrudDAOimpl<Order, Integer> implements OrderDAO {

    @Override
    public int getLastOrderId() throws Exception {
        return (int) session.createNativeQuery("SELECT id FROM `Order` ORDER BY id DESC LIMIT 1").uniqueResult();
    }
}
