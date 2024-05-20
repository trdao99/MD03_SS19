package com.ra.md03_ss18.DAO;

import com.ra.md03_ss18.entity.Employee;
import java.util.List;

public interface IEmployeeDAO {
    List<Employee> getEmployees();
    Employee getEmployeeByID(int id);
    Boolean insertEmployee(Employee employee);
    Boolean updateEmployee(Employee employee);
    void deleteEmployee(Employee employee);
}
