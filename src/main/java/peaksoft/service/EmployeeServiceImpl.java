package peaksoft.service;

import peaksoft.dao.EmployeeDao;
import peaksoft.dao.EmployeeDaoImpl;
import peaksoft.model.Employee;
import peaksoft.model.Job;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService{
    EmployeeDao employeeDao=new EmployeeDaoImpl();
    @Override
    public String createEmployee() {
        employeeDao.createEmployee();
        return "Successfully created";
    }

    @Override
    public String addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
        return "Successfully added";
    }

    @Override
    public String dropTable() {
        employeeDao.dropTable();
        return "Successfully dropped";
    }

    @Override
    public String cleanTable() {
        employeeDao.cleanTable();
        return "Successfully cleaned";
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        employeeDao.updateEmployee(id, employee);
        return "Successfully updated";
    }

    @Override
    public List<Employee> getAllEmployees() {

        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        return employeeDao.getEmployeeByPosition(position);
    }
}
