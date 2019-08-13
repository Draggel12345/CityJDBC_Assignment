package lexicon.se.anton.JDBC_Assignment;

import lexicon.se.anton.JDBC_Assignment.data.CityDaoJDBC;
import lexicon.se.anton.JDBC_Assignment.model.City;


public class App 
{
    public static void main( String[] args )
    {
        CityDaoJDBC dao = new CityDaoJDBC();
        
        dao.delete(new City(4081, null, null, null, 0));
        
    }
}
