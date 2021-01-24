package business.custom.impl;

import business.custom.OrderBO;
import dao.DAOFactory;
import dao.DAOTypes;
import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailDAO;
//import db.HibernateUtil;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import entity.Customer;
import entity.Item;
import entity.Order;
import entity.OrderDetail;
import org.hibernate.Session;
import util.FactoryConfiguration;

public class OrderBOImpl implements OrderBO {

    private OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER);
    private OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER_DETAIL);
    private ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);
    private CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);

    public void placeOrder(OrderDTO order) throws Exception {
        try (Session session =  FactoryConfiguration.getInstance().getSession()) {
            session.beginTransaction();

            orderDAO.setSession(session);
            customerDAO.setSession(session);
            itemDAO.setSession(session);
            orderDetailDAO.setSession(session);

            Customer customer = customerDAO.find(order.getCustomerId());

            orderDAO.save(new Order(order.getOrderId(), order.getOrderDate(), customer));

            for (OrderDetailDTO dto : order.getOrderDetails()) {
                orderDetailDAO.save(new OrderDetail(dto.getOrderId(), dto.getItemCode(), dto.getQty(), dto.getUnitPrice()));
                Item item = itemDAO.find(dto.getItemCode());
                int qut = item.getQtyOnHand() - dto.getQty();
                item.setQtyOnHand(qut);
            }
            session.getTransaction().commit();
        }
    }


    public int generateOrderId() throws Exception {
        try(Session session =  FactoryConfiguration.getInstance().getSession()){
            orderDAO.setSession(session);
            return orderDAO.getLastOrderId()+1;
        }catch (NullPointerException e){}
        return 1;
    }

}
