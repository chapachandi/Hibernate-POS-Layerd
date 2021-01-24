package business.custom.impl;

import business.custom.ItemBO;
import dao.DAOFactory;
import dao.DAOTypes;
import dao.custom.ItemDAO;
//import db.HibernateUtil;
import dto.ItemDTO;
import entity.Item;
import org.hibernate.Session;
import util.FactoryConfiguration;

import java.util.List;
import java.util.stream.Collectors;

public class ItemBOImpl implements ItemBO {

    private ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);

    public List<ItemDTO> getAllItems() throws Exception {

        try(Session session =  FactoryConfiguration.getInstance().getSession()){
            session.beginTransaction();
            itemDAO.setSession(session);
            List<ItemDTO> collect = itemDAO.findAll().stream().map(item -> new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand())).collect(Collectors.toList());
            session.getTransaction().commit();
            return collect;
        }
        //        itemDAO.findAll().stream().map(new Function<Item, ItemDTO>() {
//            @Override
//            public ItemDTO apply(Item item) {
//                return new ItemDTO(item.getCode(), item.getDescription(),item.getUnitPrice(), item.getQtyOnHand());
//            }
//        }).collect(Collectors.toList());

    }

    public boolean saveItem(ItemDTO item) throws Exception {
        try(Session session =  FactoryConfiguration.getInstance().getSession()){
            session.beginTransaction();
            itemDAO.setSession(session);
            itemDAO.save(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            throw ex;
        }
    }

    public boolean updateItem(ItemDTO item) throws Exception {
        try(Session session =  FactoryConfiguration.getInstance().getSession()){
            session.beginTransaction();
            itemDAO.setSession(session);
            itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            throw ex;
        }
    }

    public boolean deleteItem(String code) throws Exception {
        try(Session session =  FactoryConfiguration.getInstance().getSession()){
            session.beginTransaction();
            itemDAO.setSession(session);
            itemDAO.delete(code);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            throw ex;
        }
    }

}
