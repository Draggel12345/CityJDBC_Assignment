package lexicon.se.anton.JDBC_Assignment.data;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lexicon.se.anton.JDBC_Assignment.model.City;

public class CityDaoJDBC implements CityDao {
	
	
	public static final String INSERT = "INSERT INTO city (Name,CountryCode,District,Population)" + "VALUES(?,?,?,?)";
	public static final String FIND_BY_ID = "SELECT * FROM city WHERE ID = ?";
	public static final String FIND_BY_NAME = "SELECT * FROM city WHERE Name = ?";
	public static final String FIND_BY_COUNTRY_CODE = "SELECT * FROM city WHERE CountryCode = ?";
	public static final String FIND_BY_ALL = "SELECT * FROM city";
	public static final String UPDATE =	"UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?";
	public static final String DELETE = "DELETE FROM city WHERE ID = ?";
	
	
	public City setGeneratedKeys(City city) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet keySet = null;
		
		try {
			
			connection = Database.getConnection();
			statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			
			statement.setString(1, city.getName());
			statement.setString(2, city.getCountryCode());
			statement.setString(3, city.getDistrict());
			statement.setInt(4, city.getPopulation());
			
			
			statement.executeUpdate();
			
			
			keySet = statement.getGeneratedKeys();
			while(keySet.next()) {
				city = new City(
						keySet.getInt(1),
						city.getName(),
						city.getCountryCode(),
						city.getDistrict(),
						city.getPopulation()
						);
			}
			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				
				if(keySet != null)
					keySet.close();
				if(statement != null)
					statement.close();
				if(connection != null)
					connection.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		return city;
	}

	@Override
	public Optional<City> findById(int id) {
		City city = null;
		
		try(Connection connection = Database.getConnection();
				PreparedStatement statement = createFindByIdStatement(connection, id);
				ResultSet resultSet = statement.executeQuery()) {
			
			while(resultSet.next()) {
				city = resultSetToCity(resultSet);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return city == null ? Optional.empty() : Optional.of(city);
	}

	@Override
	public List<City> findByName(String name) {
		List<City> result = new ArrayList<>();
		try(Connection connection = Database.getConnection();
				PreparedStatement statement = createFindByNameStatement(connection, name);
				ResultSet resultSet = statement.executeQuery()) {
			
			while(resultSet.next()) {
				result.add(resultSetToCity(resultSet));
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public List<City> findByCountryCode(String countryCode) {
		List<City> result = new ArrayList<>();
		try(Connection connection = Database.getConnection();
				PreparedStatement statement = createFindByCountryCodeStatement(connection, countryCode);
				ResultSet resultSet = statement.executeQuery()) {
			
			while(resultSet.next()) {
				result.add(resultSetToCity(resultSet));
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<City> findAll() {
		List<City> result = new ArrayList<>();
		try(Connection connection = Database.getConnection();
				PreparedStatement statement = createFindByAllStatement(connection);
				ResultSet resultSet = statement.executeQuery()) {
			
			while(resultSet.next()) {
				result.add(resultSetToCity(resultSet));
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}

	@Override
	public City add(City city) {
		
		try(Connection connection = Database.getConnection();
				PreparedStatement statement = createAddByInsertStatement(connection, city);) {
			
			statement.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return city;
	}

	@Override
	public City update(City city) {
		
		try(Connection connection = Database.getConnection();
				PreparedStatement statement = createUpdateStatement(connection, city);) {
			
			statement.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return city;
	}

	@Override
	public int delete(City city) {
		
		int cityToDelete = city.getId();
		
		try(Connection connection = Database.getConnection();
				PreparedStatement statement = createDeleteStatement(connection, city);) {
			
			statement.setInt(1, cityToDelete);
			statement.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return cityToDelete;
	}

	private PreparedStatement createFindByIdStatement(Connection connection, int id) throws SQLException {
		    PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
		    statement.setInt(1, id);
		    return statement;
	}
	
	private PreparedStatement createFindByNameStatement(Connection connection, String name) throws SQLException {
		    PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME);
		    statement.setString(1, name);
		    return statement;
	}
	
	private PreparedStatement createFindByCountryCodeStatement(Connection connection, String countryCode) throws SQLException {
	        PreparedStatement statement = connection.prepareStatement(FIND_BY_COUNTRY_CODE);
	        statement.setString(1, countryCode);
	        return statement;
    }
	
	private PreparedStatement createFindByAllStatement(Connection connection) throws SQLException {
	        PreparedStatement statement = connection.prepareStatement(FIND_BY_ALL);
	        return statement;
    }
	 
    private PreparedStatement createAddByInsertStatement(Connection connection, City city) throws SQLException {
    	    String name = null;
    	    String countryCode = null;
    	    String district = null;
    	    int population = 0;
    	    PreparedStatement statement = connection.prepareStatement(INSERT);
    	    statement.setString(1, city.setName(name));
    	    statement.setString(2, city.setCountryCode(countryCode));
    	    statement.setString(3, city.setDistrict(district));
    	    statement.setInt(4, city.setPopulation(population));
    	    return statement;
    }
    
	private PreparedStatement createUpdateStatement(Connection connection, City city) throws SQLException {
	  	    PreparedStatement statement = connection.prepareStatement(UPDATE);
		    statement.setString(1, city.getName());
		    statement.setString(2, city.getCountryCode());
		    statement.setString(3, city.getDistrict());
		    statement.setInt(4, city.getPopulation());
		    statement.setInt(5, city.getId());
		    return statement;
	}
	
	private PreparedStatement createDeleteStatement(Connection connection, City city) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(DELETE);
		return statement;
	}
	
    private City resultSetToCity(ResultSet resultSet) throws SQLException {
		return new City(resultSet.getInt("ID"),
				resultSet.getString("Name"),
				resultSet.getString("countryCode"),
				resultSet.getString("District"),
				resultSet.getInt("Population"));
	}
    
}
