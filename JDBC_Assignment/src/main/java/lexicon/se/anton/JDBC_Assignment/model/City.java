package lexicon.se.anton.JDBC_Assignment.model;

public class City {

	private int id;
	private String name;
	private String countryCode;
	private String district;
	private int population;

	public City(int id, String name, String countryCode, String district, int population) {
		this(name, countryCode, district, population);
		this.id = id;
	}

	public City(String name, String countryCode, String district, int population) {
		this.name = name;
		this.countryCode = countryCode;
		this.district = district;
		this.population = population;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String setName(String name) {
		return this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String setCountryCode(String countryCode) {
		return this.countryCode = countryCode;
	}

	public String getDistrict() {
		return district;
	}

	public String setDistrict(String district) {
		return this.district = district;
	}

	public int getPopulation() {
		return population;
	}

	public int setPopulation(int population) {
		return this.population = population;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((district == null) ? 0 : district.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + population;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (population != other.population)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("City [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", countryCode=");
		builder.append(countryCode);
		builder.append(", district=");
		builder.append(district);
		builder.append(", population=");
		builder.append(population);
		builder.append("]");
		return builder.toString();
	}
}
