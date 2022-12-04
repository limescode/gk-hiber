package pl.limescode.hiber.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

@Service
public class SessionFactoryService {

    private SessionFactory factory;

    public void init() {
        factory = new Configuration()
                .configure("hibernate.cfx.xml")
                .buildSessionFactory();
    }

    public Session getSession() {
        return factory.getCurrentSession();
    }

    public void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }

}
