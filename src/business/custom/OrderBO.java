package business.custom;

import business.SuperBO;
import dto.OrderDTO;

public interface OrderBO extends SuperBO {

    public void placeOrder(OrderDTO order) throws Exception;

    public int generateOrderId() throws Exception;

}
