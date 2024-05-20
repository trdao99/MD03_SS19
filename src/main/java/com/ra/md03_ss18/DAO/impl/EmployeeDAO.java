package com.ra.md03_ss18.DAO.impl;

import com.ra.md03_ss18.DAO.IEmployeeDAO;
import com.ra.md03_ss18.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAO implements IEmployeeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        Session session = sessionFactory.openSession();
        employees = session.createQuery("from Employee").list();
        session.close();
        return employees;
    }

    @Override
    public Employee getEmployeeByID(int id) {
        Session session = sessionFactory.openSession();
        Employee employee = session.get(Employee.class, id);
        session.close();
        return employee;
    }

    @Override
    public Boolean insertEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Boolean updateEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public void deleteEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(employee);
        session.getTransaction().commit();
        session.close();
    }
}
