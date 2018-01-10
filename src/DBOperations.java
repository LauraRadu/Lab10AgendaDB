import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by condor on 26/02/15.
 * FastTrackIT, 2015
 * <p/>
 * DEMO ONLY PURPOSES, IT MIGHT CONTAINS INTENTIONALLY ERRORS OR ESPECIALLY BAD PRACTICES
 * <p>
 * make sure you refactor it and remove lots of bad practices like loading the driver multiple times or
 * repeating the same common code multiple times
 * <p>
 * BTW, exercise 1: how we reorg this/refactor in small pieces
 */

public class DBOperations {

    public static List read() throws ClassNotFoundException, SQLException {
        // 1. load driver, no longer needed in new versions of JDBC
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/laura7";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();


        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT username,PHONENUMBER FROM AGENDA");

        // 6. iterate the result set and print the values
        List<Person> listaDeUsers = new ArrayList();
        while (rs.next()) {

            Person u = new Person();
            u.setName(rs.getString("username").trim());
            u.setPhoneNumber(rs.getString("phonenumber").trim());
            listaDeUsers.add(u);

        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();

        return listaDeUsers;
    }


    public static void addAgenda(Person p) throws ClassNotFoundException, SQLException {

        // 1. load driver in JVM, no longer needed in new versions of JDBC
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/laura7";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO AGENDA (userNAME, PHONENUMBER) VALUES (?,?)");

        pSt.setString(1, p.getName());
        pSt.setString(2, p.getPhoneNumber());

        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        // 6. close the objects, sa nu mai ramana conexiune la retea
        pSt.close();
        conn.close();
    }


    public static void delete(Person p) throws ClassNotFoundException, SQLException {

        // 1. load driver, no longer needed in new versions of JDBC
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/laura7";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("DELETE FROM agenda WHERE username=?");
        pSt.setString(1, p.getName());

        // 5. execute a prepared statement
        int rowsDeleted = pSt.executeUpdate();
        System.out.println(rowsDeleted + " rows were deleted.");

        // 6. close the objects
        pSt.close();
        conn.close();
    }


    public static List findPerson(Person p)  throws ClassNotFoundException, SQLException{

        // 1. load driver, no longer needed in new versions of JDBC
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/laura7";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("SELECT username, phonenumber FROM agenda WHERE username like concat('%',?,'%')");
        pSt.setString(1, p.getName());
        ResultSet rs = pSt.executeQuery();

        List<Person> listOfUsers = new ArrayList();
        while (rs.next()) {

            Person u = new Person();
            u.setName(rs.getString("username").trim());
            u.setPhoneNumber(rs.getString("phonenumber").trim());
            listOfUsers.add(u);

        }

        // 6. close the objects
        pSt.close();
        conn.close();

        return listOfUsers;
    }


    public static void modify(Person p, String newName, String newPhone) throws ClassNotFoundException, SQLException {

        // 1. load driver, no longer needed in new versions of JDBC
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/laura7";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("UPDATE agenda SET username=?, phonenumber=? WHERE username=?"); //so we have 3 params

        pSt.setString(1, newName);
        pSt.setString(2, newPhone);
        pSt.setString(3, p.getName());

        // 5. execute a prepared statement
        int rowsUpdated = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }
}



