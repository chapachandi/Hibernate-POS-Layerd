package business;

import business.custom.impl.CustomerBOImpl;
import business.custom.impl.ItemBOImpl;
import business.custom.impl.OrderBOImpl;

public class BOFactory {

    private static business.BOFactory boFactory;

    private BOFactory() {

    }

    public static business.BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new business.BOFactory();
        }
        return boFactory;
    }

    public <T extends SuperBO> T getBO(BOType boType) {
        switch (boType) {
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case ORDER:
                return (T) new OrderBOImpl();
            default:
                return null;
        }
    }

}
