import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{


    @Override
    public void add(Employee employee) {
        try(PreparedStatement statement = ConnectionUtils.getConnection().prepareStatement(
                    "INSERT INTO employee (id, first_name, last_name, gender, age, city_id) VALUES ((?), (?), (?), (?), (?), (?))")) {
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getGender());
            statement.setInt(5, employee.getAge());
            statement.setInt(6, employee.getCityId());
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getById(int id) {
    Employee employee = new Employee();
        try(PreparedStatement statement = ConnectionUtils.getConnection().prepareStatement(
                    "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id WHERE id = (?)")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setCity(new City(resultSet.getInt("city_id"),
                        resultSet.getString("city_name")));
            }

    } catch (SQLException e) {
        e.printStackTrace();
    }
            return employee;
        }


    @Override
    public List<Employee> readAll() {
        List<Employee> employeeList = new ArrayList<>();
        try(PreparedStatement statement = ConnectionUtils.getConnection().prepareStatement(
                    "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = Integer.parseInt(resultSet.getString("age"));
                City city = new City(resultSet.getInt("city_id"),
                        resultSet.getString("city_name"));
                employeeList.add(new Employee(id, firstName, lastName, gender, age, city));
            }
        }
     catch (SQLException e) {
        e.printStackTrace();
    }
        return employeeList;
    }

    @Override
    public void updateEmployeeById(int id) {
        Employee employee = new Employee();
        try(PreparedStatement statement = ConnectionUtils.getConnection().prepareStatement(
                    "UPDATE employee WHERE id = (?)")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setCityId(resultSet.getInt("city_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try(PreparedStatement statement = ConnectionUtils.getConnection().prepareStatement(
            "DELETE FROM employee WHERE id=(?)")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

