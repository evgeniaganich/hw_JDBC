import java.sql.*;
import java.util.List;

public class Application {


    public static void main(String[] args) throws SQLException {

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee tatiana = new Employee("Tatiana", "Larina", "female", 18, 2);
        employeeDAO.add(tatiana);

        System.out.println(employeeDAO.getById(1));

        Employee maria = new Employee(1, "Maria", "Kozlova", "female", 27, 1);
        employeeDAO.updateEmployee(maria);

        List<Employee> list = employeeDAO.readAll();

        for (Employee employee : list) {
            System.out.println(employee);
        }
        employeeDAO.deleteEmployee(tatiana);

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
}





