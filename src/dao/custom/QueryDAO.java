package dao.custom;

import entity.CustomEntity;
import dao.SuperDAO;
import entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO {

    List<CustomEntity> getOrdersTotal() throws Exception;

}
