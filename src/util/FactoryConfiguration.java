package util;

import entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static util.FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;
    private FactoryConfiguration(){
        Configuration configure = new Configuration().configure()
                .addAnnotatedClass(Customer.class);
        sessionFactory = configure.buildSessionFactory();
    }

    public static util.FactoryConfiguration getInstance(){
        return (factoryConfiguration == null) ? factoryConfiguration = new util.FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
