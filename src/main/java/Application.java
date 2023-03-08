import java.sql.*;
import java.util.List;

public class Application {


    public static void main(String[] args) throws SQLException {

        try(final Connection connection = DriverManager.getConnection(ConnectionUtils.URL, ConnectionUtils.USER, ConnectionUtils.PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee FULL OUTER JOIN city " +
                    "ON employee.city_id = city.city_id WHERE id = (?)");
            statement.setInt(1, 1);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                String firstName = "Имя: " + resultSet.getString("first_name");
                String lastName = "Фамилия: " + resultSet.getString("last_name");
                String gender = "Пол: " + resultSet.getString("gender");
                String age = "Возраст: " + resultSet.getInt("age");
                String city = "Город: " + resultSet.getString("city_name");

                System.out.println(firstName);
                System.out.println(lastName);
                System.out.println(gender);
                System.out.println(age);
                System.out.println(city);

                EmployeeDAO employeeDAO = new EmployeeDAOImpl();
//                Employee anna = new Employee("Anna", "Karenina", "female", 28, 2);
//                employeeDAO.add(anna);
                List<Employee> list = employeeDAO.readAll();
//                employeeDAO.deleteById(9);
//                employeeDAO.deleteById(0);
                for (Employee employee: list) {
                    System.out.println(employee);
                }
            }
        }


        }
    }




