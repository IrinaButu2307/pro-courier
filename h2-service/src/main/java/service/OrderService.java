package service;

import com.procourier.model.Order;
import dao.OrderDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.function.Consumer;

//http://www.learn4master.com/design-pattern/java-singleton-pattern

public final class OrderService {
    private final EntityManagerFactory emf;

    private static final String PERSISTENCE_UNIT_NAME = "pro-courierPU";

    private OrderService() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    private final static class SingletonHolder {
        private final static OrderService INSTANCE = new OrderService();
    }

    public static OrderService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addOrder(Order order) {
        final EntityManager em = emf.createEntityManager();
        final OrderDao orderDao = new OrderDao(em);

        //orice modif de INSERT, UPDATE, DELETE se face intr-o tranzactie
        runInTransaction(order, orderDao::addOrder, em.getTransaction());
    }

    private static void runInTransaction(Order order, Consumer<Order> operation, EntityTransaction transaction) {
        transaction.begin();
        operation.accept(order);
        transaction.commit();
    }

    public Order getOrderById(Long id) {
        final var em = emf.createEntityManager();
        final var orderDao = new OrderDao(em);
        return orderDao.getOrderById(id);
    }

    public List<Order> getOrders() {
        final var em = emf.createEntityManager();
        final var orderDao = new OrderDao(em);
        return orderDao.getOrders();
    }
}
