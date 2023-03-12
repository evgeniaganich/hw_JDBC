import java.sql.*;
import java.util.List;

public class Application {


    public static void main(String[] args) throws SQLException {

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee tatiana = new Employee("Tatiana", "Larina", "female", 18, 2);
//        employeeDAO.add(tatiana);
//
//        System.out.println(employeeDAO.getById(1));
//
//        Employee maria = new Employee(1, "Maria", "Kozlova", "female", 27, 1);
//        employeeDAO.updateEmployee(maria);
//
//        List<Employee> list = employeeDAO.readAll();
//
//        for (Employee employee : list) {
//            System.out.println(employee);
//        }
//        employeeDAO.deleteEmployee(tatiana);
//
//        for (Employee employee : list) {
//            System.out.println(employee);
//        }

        CityDao cityDao = new CityDAOImpl();

        List<Employee> employeesFromDubai = List.of(tatiana);
        City dubai = new City("Dubai", employeesFromDubai);
        cityDao.add(dubai);

        List<City> cityList = cityDao.readAll();

        for (City city: cityList) {
            System.out.println(city);
        }
        cityDao.getById(1);

        City uryupinsk = new City(7, "Uryupinsk");

        cityDao.updateCity(uryupinsk);

        for (City city: cityList) {
            System.out.println(city);
        }

        cityDao.deleteCity(uryupinsk);
        for (City city: cityList) {
          System.out.println(city);}


    }
}





