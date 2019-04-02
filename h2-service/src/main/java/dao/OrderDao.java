package dao;

import com.procourier.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public final class OrderDao {
    private final EntityManager em;

    public OrderDao(EntityManager em) {
        this.em = em;
    }

    public void addOrder(Order order) {
        em.persist(order);
    }

    public Order getOrderById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> getOrders() {
        final String sql = "SELECT o FROM Order o";
        TypedQuery<Order> query = em.createQuery(sql, Order.class);
        return query.getResultList();
    }

}
