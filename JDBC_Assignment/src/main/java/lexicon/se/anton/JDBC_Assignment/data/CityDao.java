package lexicon.se.anton.JDBC_Assignment.data;

import java.util.List;
import java.util.Optional;

import lexicon.se.anton.JDBC_Assignment.model.City;

public interface CityDao {

	Optional<City> findById(int id);
	List<City> findByName(String name);
	List<City> findByCountryCode(String countryCode);
	List<City> findAll();
	City add(City city);
	City update(City city);
	int delete(City city);
}
