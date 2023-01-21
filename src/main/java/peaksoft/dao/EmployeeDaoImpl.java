package peaksoft.dao;

import peaksoft.config.Configuration;
import peaksoft.model.Employee;
import peaksoft.model.Job;

import java.sql.*;
import java.util.*;

public class EmployeeDaoImpl implements EmployeeDao{
    private Connection connection;

    public EmployeeDaoImpl(){
        this.connection= Configuration.getConnection();
    }
    @Override
    public void createEmployee() {
        String sql= """
                create table employees(
                id serial primary key,
                first_name varchar(50) not null,
                last_name varchar (50) not null,
                age int ,
                email varchar unique,
                job_id int references jobs(id));
                """;
        try (Statement statement = connection.createStatement();){
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void addEmployee(Employee employee) {
        String sql= """
                insert into employees(first_name,last_name,age,email,job_id)
                values(?,?,?,?,?);
               """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5, employee.getJobId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void dropTable() {
        String sql= """
                drop table employees;
                """;
        try (Statement statement = connection.createStatement();){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void cleanTable() {
        String sql= """
                delete from employees;
                """;

        try(Statement statement = connection.createStatement();) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        String sql= """
                update employees
                set first_name=?,
                last_name=?,
                age=?,
                email=?,
                job_id=?
                where id=?;
                """;
        try (  PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5,employee.getJobId());
            preparedStatement.setLong(6,id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee>employeeList=new ArrayList<>();
        Employee employee=new Employee();
        String sql= """
                select * from employees;
                """;
        try( Statement statement = connection.createStatement();) {
            ResultSet resultSet= statement.executeQuery(sql);
            while (resultSet.next()){
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setAge(resultSet.getInt(4));
                employee.setEmail(resultSet.getString(5));
                employee.setJobId(resultSet.getInt(6));
                employeeList.add(employee);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    @Override
    public Employee findByEmail(String email) {
        String sql= """
                select * from employees where email=?;
                """;
        try( PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            Employee employee=new Employee();
            while (resultSet.next()){
                employee.setId(resultSet.getLong(1));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setAge(resultSet.getInt(4));
                employee.setEmail(resultSet.getString(5));
                employee.setJobId(resultSet.getInt(6));
                System.out.println("Successfully found");
            }
            return employee;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee,Job>employeeJobMap=new LinkedHashMap<>();

        String sql= """
                select * from employees  join jobs on 
                employees.job_id=jobs.id where employees.id=?;
                """;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Employee employee1=new Employee();
                Job job1=new Job();

                employee1.setId(resultSet.getLong("id"));
                employee1.setFirstName(resultSet.getString(2));
                employee1.setLastName(resultSet.getString(3));
                employee1.setAge(resultSet.getInt(4));
                employee1.setEmail(resultSet.getString(5));
                employee1.setJobId(resultSet.getInt(6));


                job1.setId(resultSet.getLong("id"));
                job1.setPosition(resultSet.getString("position"));
                job1.setProfession(resultSet.getString("profession"));
                job1.setDescription(resultSet.getString("description"));
                job1.setExperience(resultSet.getInt("experience"));
                employeeJobMap.put(employee1,job1);
                return employeeJobMap;

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employeeJobMap;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee>employeeList2=new ArrayList<>();
        String sql= """
                select * from employees join  jobs on 
                employees.id = jobs.id  where jobs.position=?;
                """;
        try( PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1,position);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Successfully get");
            while (resultSet.next()){
                Employee employee2=new Employee();
                employee2.setId(resultSet.getLong("id"));
                employee2.setFirstName(resultSet.getString(2));
                employee2.setLastName(resultSet.getString(3));
                employee2.setAge(resultSet.getInt(4));
                employee2.setEmail(resultSet.getString(5));
                employee2.setJobId(resultSet.getInt(6));
                employeeList2.add(employee2);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employeeList2;
    }
}
