package com.huixu.travel.dao;

import com.huixu.travel.entity.Authorities;
import com.huixu.travel.entity.POIs;
import com.huixu.travel.entity.Plan;
import com.huixu.travel.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlanDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void save(Plan plan) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(plan);
            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public List<Plan> getAllPlans(String email) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, email);
            if (user != null) {
                return user.getPlanList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

}
