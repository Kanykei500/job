package peaksoft;

import peaksoft.model.Employee;
import peaksoft.model.Job;
import peaksoft.service.EmployeeService;
import peaksoft.service.EmployeeServiceImpl;
import peaksoft.service.JobService;
import peaksoft.service.JobServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeServiceImpl();
        JobService service1 = new JobServiceImpl();
        while (true) {
            System.out.println("""
                    ~~~~~~~JOBS~~~~~~~~~~
                    1-CREATE TABLE JOBS
                    2-ADD JOB
                    3-GET JOB BY ID
                    4-SORT BY EXPERIENCE
                    5-GET JOB BY EMPLOYEE ID
                    6-DELETE DESCRIPTION COLUMN
                                        
                    ~~~~~~EMPLOYEES~~~~~~~
                    7-CREATE EMPLOYEE
                    8-ADD EMPLOYEE
                    9-DROP TABLE
                    10-CLEAN TABLE
                    11-UPDATE EMPLOYEE
                    12-GET ALL EMPLOYEES
                    13-FIND BY EMAIL
                    14-GET EMPLOYEE BY ID
                    15-GET EMPLOYEE BY POSITION
                                        
                    WRITE COMMAND:
                    """);
            int number = new Scanner(System.in).nextInt();
            switch (number) {
                case 1:
                    System.out.println(service1.createJobTable());
                    break;
                case 2:
                    System.out.println(service1.addJob(new Job("Management",
                            "Java", "Backend developer", 3)));
                    System.out.println(service1.addJob(new Job("Instructor",
                            "Java", "Backend developer", 5)));
                    System.out.println(service1.addJob(new Job("Mentor",
                            "JavaScript", "Frontend developer", 2)));
                    System.out.println(service1.addJob(new Job("Management",
                            "JavaScript", "Frontend developer", 6)));
                    System.out.println(service1.addJob(new Job("Mentor",
                            "Java", "Backend developer", 4)));
                    break;
                case 3:
                    System.out.println(service1.getJobById(1L));
                    break;
                case 4:
                    System.out.println(service1.sortByExperience("desc"));
                    break;
                case 5:
                    System.out.println(service1.getJobByEmployeeId(4L));
                    break;
                case 6:
                    System.out.println(service1.deleteDescriptionColumn());
                    break;
                case 7:
                    System.out.println(service.createEmployee());
                    break;
                case 8:
                    System.out.println(service.addEmployee(new Employee("Aijamal eje ",
                            "Asangazieva", 23, "a@gmail.com", 2)));
                    System.out.println(service.addEmployee(new Employee("Muhammed agai ",
                            "Toichubai uulu", 22, "m@gmail.com", 3)));
                    System.out.println(service.addEmployee(new Employee("Rahim agai ",
                            "Bazarbay uulu ", 20, "r@gmail.com", 5)));
                    System.out.println(service.addEmployee(new Employee("Adina eje ",
                            "Sharshekeeva", 27, "adina@gmail.com", 1)));
                    System.out.println(service.addEmployee(new Employee("Muna eje ",
                            "Aitbekova", 20, "muna@gmail.com", 4)));
                    break;
                case 9:
                    System.out.println(service.dropTable());
                    break;
                case 10:
                    System.out.println(service.cleanTable());
                    break;
                case 11:
                    System.out.println(service.updateEmployee(2L, new Employee("Maksat agai",
                            "Akylov", 28, "maksat@gmail.com", 2)));
                    break;
                case 12:
                    System.out.println(service.getAllEmployees());
                    break;
                case 13:
                    System.out.println(service.findByEmail("a@gmail.com"));
                    break;
                case 14:
                    System.out.println(service.getEmployeeById(1L));
                    break;
                case 15:
                    System.out.println(service.getEmployeeByPosition("Management"));
                    break;


            }
        }
    }
}
