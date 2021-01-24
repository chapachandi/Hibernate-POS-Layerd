package dao;

import dao.custom.impl.*;

public class DAOFactory {

    private static dao.DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static dao.DAOFactory getInstance(){
        if (daoFactory == null){
            daoFactory = new dao.DAOFactory();
        }
        return daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoType){
        switch (daoType){
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case ORDER:
                return (T) new OrderDAOImpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }

}
