import java.sql.*;

public class Assignment2 {

	// A connection to the database
	Connection connection;

	// Statement to run queries
	Statement sql;

	// Prepared Statement
	PreparedStatement ps;

	// Resultset for the query
	ResultSet rs;

	// CONSTRUCTOR
	Assignment2() {
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Registered");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Using the input parameters, establish a connection to be used for this
	// session. Returns true if connection is sucessful

	public boolean connectDB(String URL, String username, String password) {

		try {
			connection = DriverManager.getConnection(URL, username, password);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// Closes the connection. Returns true if closure was sucessful
	public boolean disconnectDB() {

		try {
			connection.close();
			return connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean makeOceanAccess() {
		try {
			sql = connection.createStatement();
			String sqlText = "CREATE TABLE oceanAccess (\n" + "    cid 		INTEGER 	PRIMARY KEY,\n"
					+ "    oid 		VARCHAR(20)	NOT NULL); ";

			System.out.println("Executing this command: \n" + sqlText.replaceAll("\\s+", " ") + "\n");
			sql.executeUpdate(sqlText);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertOceanAccess(int cid, int oid) {

		try {

			sql = connection.createStatement();
			String insertOceanAccess = "INSERT INTO oceanaccess VALUES ('" + cid + "','" + oid + "');";
			System.out.println("Executing this command: \n" + insertOceanAccess.replaceAll("\\s+", " ") + "\n");
			sql.executeUpdate(insertOceanAccess);
			sql.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean makeNeighbour() {
		try {
			sql = connection.createStatement();
			String sqlText = "CREATE TABLE neighbour (\n" + "    country 		INTEGER 	PRIMARY KEY,\n"
					+ "    neighbor 		INTEGER	NOT NULL, length INTEGER NOT NULL); ";

			System.out.println("Executing this command: \n" + sqlText.replaceAll("\\s+", " ") + "\n");
			sql.executeUpdate(sqlText);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertNeighbour(int country, int neighbour, int length) {

		try {

			sql = connection.createStatement();
			String insertOceanAccess = "INSERT INTO neighbour VALUES ('" + country + "','" + neighbour + "','" + length
					+ "');";
			System.out.println("Executing this command: \n" + insertOceanAccess.replaceAll("\\s+", " ") + "\n");
			sql.executeUpdate(insertOceanAccess);
			sql.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public String checkNeighbour() {
		String out = "";
		try {
			sql = connection.createStatement();
			String query = "SELECT * FROM neighbour;";
			System.out.println("Executing this command: \n" + query.replaceAll("\\s+", " ") + "\n");
			rs = sql.executeQuery(query);

			if (rs != null) {
				while (rs.next()) {
					System.out.println("country = " + rs.getInt(1) + ";neighbor = " + rs.getInt(2) + ";");
				}
			}
			System.out.println();
			return out;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "ERROR!!";
	}

	public void dropNeighbour() {
		try {
			sql = connection.createStatement();
			String query = "DROP TABLE neighbour;";
			System.out.println("Executing this command: \n" + query.replaceAll("\\s+", " ") + "\n");
			sql.executeQuery(query);
			sql.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean makeLanguage() {
		try {
			sql = connection.createStatement();
			String sqlText = "CREATE TABLE language (\n" + "    cid 		INTEGER 	,\n"
					+ "    lid 		INTEGER 	NOT NULL,\n" + "    lname 		VARCHAR(20) NOT NULL,\n"
					+ "    lpercentage	REAL 		NOT NULL,\n" + "	PRIMARY KEY(cid, lid));";

			System.out.println("Executing this command: \n" + sqlText.replaceAll("\\s+", " ") + "\n");
			sql.executeUpdate(sqlText);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertLanguage(int cid, int lid, String lname, int lpercentage) {

		try {

			sql = connection.createStatement();
			String insertLanguage = "INSERT INTO language VALUES ('" + cid + "','" + lid + "','" + lname + "','"
					+ lpercentage + "'  );";
			System.out.println("Executing this command: \n" + insertLanguage.replaceAll("\\s+", " ") + "\n");
			sql.executeUpdate(insertLanguage);
			sql.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public void dropLanguage() {
		try {
			sql = connection.createStatement();
			String query = "DROP TABLE language;";
			System.out.println("Executing this command: \n" + query.replaceAll("\\s+", " ") + "\n");
			sql.executeQuery(query);
			sql.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String checkLanguage() {
		String out = "";
		try {
			sql = connection.createStatement();
			String query = "SELECT * FROM language;";
			System.out.println("Executing this command: \n" + query.replaceAll("\\s+", " ") + "\n");
			rs = sql.executeQuery(query);

			if (rs != null) {
				while (rs.next()) {
					System.out.println("cid = " + rs.getInt(1) + ";lid = " + rs.getInt(2) + "lname = " + rs.getString(3)
							+ "; " + "lpercentage = " + rs.getString(4));
				}
			}
			System.out.println();
			return out;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "ERROR!!";
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean insertCountry(int cid, String name, int height, int population) {
		try {

			String query = "SELECT * FROM country;";
			sql = connection.createStatement();
			rs = sql.executeQuery(query);

			while (rs.next()) {
				if (rs.getObject(1).equals(cid)) {
					return false;
				}
			}
			rs.close();

			sql = connection.createStatement();
			String insertCountry = "INSERT INTO country VALUES ('" + cid + "','" + name + "','" + height + "','"
					+ population + "');";
			System.out.println("Executing this command: \n" + insertCountry.replaceAll("\\s+", " ") + "\n");
			sql.executeUpdate(insertCountry);
			sql.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public int getCountriesNextToOceanCount(int oid) {
		// String sqlText = "SELECT COUNT(c.cid) AS count FROM oceanaccess AS oa,
		// country AS c\n" + "WHERE c.cid=oa.cid;\n";
		// System.out.println("Executing this command: \n" + sqlText.replaceAll("\\s+",
		// " ") + "\n");
		// sql.executeUpdate(sqlText);
		try {
			sql = connection.createStatement();
			String sqlText = "SELECT COUNT(oid) AS a FROM oceanaccess WHERE oid = " + oid + ";";
			rs = sql.executeQuery(sqlText);
			// return rs.getInt(1);
			while (rs.next()) {
				return rs.getInt("a");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void dropOceanAccess() {
		try {
			sql = connection.createStatement();
			String query = "DROP TABLE oceanaccess";
			System.out.println("Executing this command: \n" + query.replaceAll("\\s+", " ") + "\n");
			sql.executeQuery(query);
			sql.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getOceanInfo(int oid) {
		String out = "";

		try {
			sql = connection.createStatement();
			String query = "SELECT * FROM ocean";
			System.out.println("Executing this command: \n" + query.replaceAll("\\s+", " ") + "\n");
			rs = sql.executeQuery(query);

			if (rs != null) {
				while (rs.next()) {
					if (!rs.getObject(1).equals(oid)) {
						return "";
					} else {
						System.out.println("oid:" + rs.getInt("oid") + " oname:" + rs.getString(2) + " depth:"
								+ rs.getInt("depth") + "\n");
					}
				}
			}
			sql.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "";
	}

	public boolean chgHDI(int cid, int year, float newHDI) {
		try {
			sql = connection.createStatement();
			String query = "UPDATE hdi SET hdi_score = " + newHDI + " WHERE year = " + year + " AND cid = " + cid;
			System.out.println("Executing this command: \n" + query.replaceAll("\\s+", " ") + "\n");
			sql.executeQuery(query);
			sql.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean deleteNeighbour(int c1id, int c2id) {
		try {
			sql = connection.createStatement();
			String insertCountry = "DELETE FROM neighbour WHERE country = " + c1id + " AND neighbor = " + c2id + ";";
			System.out.println("Executing this command: \n" + insertCountry.replaceAll("\\s+", " ") + "\n");
			sql.executeUpdate(insertCountry);

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String listCountryLanguages(int cid) {
		String out = "nmnbmv";
		try {
			sql = connection.createStatement();
			String query = "SELECT lid, lname, ((lpercentage * country.population) / 100) AS population FROM country, language "
					+ "WHERE language.cid= " + cid + " AND country.cid=language.cid;";
			System.out.println("Executing this command: \n" + query.replaceAll("\\s+", " ") + "\n");
			rs = sql.executeQuery(query);
			int counter = 1;

			if (rs != null) {
				while (rs.next()) {
					System.out.println("|" + counter + rs.getInt(1) + ":|" + counter + "|" + rs.getString(2) + ":|"
							+ counter + "|" + rs.getInt(3));
					counter++;
				}
			}
			rs.close();
			sql.close();
			return out;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "error";
	}

	public boolean updateHeight(int cid, int decrH) {
		try {
			sql = connection.createStatement();
			String query = "UPDATE country SET height = " + decrH + " WHERE cid = " + cid;
			System.out.println("Executing this command: \n" + query.replaceAll("\\s+", " ") + "\n");
			sql.executeQuery(query);
			sql.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	//
	// public String checkMostPopulous() {
	// String out = "";
	// try {
	// sql = connection.createStatement();
	// String query = "SELECT cid,cname FROM country WHERE population > 1000000";
	// System.out.println("Executing this command: \n" + query.replaceAll("\\s+", "
	// ") + "\n");
	// rs = sql.executeQuery(query);
	//
	// if (rs != null) {
	// while (rs.next()) {
	// System.out.println();
	// }
	// }
	// System.out.println();
	// return out;
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// return "ERROR!!";
	// }

	public boolean updateDB() {
		try {
		    String sqlText = "CREATE TABLE a2.mostPopulousCountries (" +
		              "cid INTEGER ," +
		              " cname VARCHAR(20) , PRIMARY KEY (cid))";
		    sql.executeUpdate(sqlText);
		    String sqlQuery;
		    sqlQuery = "INSERT INTO a2.mostPopulousCountries (" +
		                "SELECT cid, cname FROM a2.country WHERE population > 100000000) " +
				  " ORDER BY population";
		    int numRows = sql.executeUpdate(sqlQuery);
		    if(numRows >= 1) {
		       return true;
		    } else {
		       return false;
		    }
		  }
		  catch (SQLException e) {
		    //e.printStackTrace();
		    return false;
		  }

		}

	private void makeMostPopulousCountries() {
		try {
			sql = connection.createStatement();
			String makeCountry = "CREATE TABLE mostPopulousCountries(cid INTEGER, cname VARCHAR(20));";
			System.out.println("Executing this command: \n" + makeCountry.replaceAll("\\s+", " ") + "\n");
			sql.executeUpdate(makeCountry);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void makeCountry() {
		try {
			sql = connection.createStatement();
			String makeCountry = "CREATE TABLE country(cid INTEGER, name VARCHAR(50), height INTEGER, population INTEGER);";
			System.out.println("Executing this command: \n" + makeCountry.replaceAll("\\s+", " ") + "\n");
			sql.executeUpdate(makeCountry);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void makeOcean() {
		try {
			sql = connection.createStatement();
			String makeOcean = "CREATE TABLE ocean (" + "    oid 		INTEGER 	PRIMARY KEY,\n"
					+ "    oname 		VARCHAR(20) NOT NULL,\n" + "    depth 		INTEGER 	NOT NULL);";
			System.out.println("Executing this command: \n" + makeOcean.replaceAll("\\s+", " ") + "\n");
			sql.executeQuery(makeOcean);
			System.out.println("Completed successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected boolean insertOcean(int oid, String oname, int depth) {
		try {
			sql = connection.createStatement();
			String insertCountry = "INSERT INTO ocean VALUES ('" + oid + "','" + oname + "','" + depth + "');";
			System.out.println("Executing this command: \n" + insertCountry.replaceAll("\\s+", " ") + "\n");
			sql.executeUpdate(insertCountry);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	protected String checkCountry() {
		String out = "";
		try {
			sql = connection.createStatement();
			String query = "SELECT * FROM country";
			System.out.println("Executing this command: \n" + query.replaceAll("\\s+", " ") + "\n");
			rs = sql.executeQuery(query);

			if (rs != null) {
				while (rs.next()) {
					System.out.println("cid = " + rs.getInt("cid") + ";cname = " + rs.getString(2) + "; height = "
							+ rs.getInt("height") + "; population = " + rs.getInt("population") + ";\n");
				}
			}
			System.out.println();
			return out;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "ERROR!!";
	}

	protected void dropCountry() {
		try {
			sql = connection.createStatement();
			String drop = "DROP TABLE country;";
			System.out.println("Executing this command: \n" + drop.replaceAll("\\s+", " ") + "\n");
			sql.executeQuery(drop);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
