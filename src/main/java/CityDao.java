import java.util.List;

public interface CityDao {
    void add(City city);
    City getById(int id);
    List<City> readAll();
    void updateCity(City city);
    void deleteCity(City city);
}
