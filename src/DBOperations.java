import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
     * Created by condor on 26/02/15.
     * FastTrackIT, 2015
     * <p/>
     * DEMO ONLY PURPOSES, IT MIGHT CONTAINS INTENTIONALLY ERRORS OR ESPECIALLY BAD PRACTICES
     *
     * make sure you refactor it and remove lots of bad practices like loading the driver multiple times or
     * repeating the same common code multiple times
     *
     * BTW, exercise 1: how we reorg this/refactor in small pieces
     */

    public class DBOperations {

        public static void demoCreate() throws ClassNotFoundException, SQLException {

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
            Scanner sc = new Scanner(System.in);
            System.out.print("Insert name: ");
            String name = sc.nextLine();

            System.out.print("Insert phonenumber ");
            String phone = sc.nextLine();
            pSt.setString(1, name);
            pSt.setString(2, phone);

            // 5. execute a prepared statement
            int rowsInserted = pSt.executeUpdate();

            // 6. close the objects, sa nu mai ramana conexiune la retea
            pSt.close();
            conn.close();
        }

        public static List demoRead() throws ClassNotFoundException, SQLException {
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

        public static void demoUpdate() throws ClassNotFoundException, SQLException {

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
            Scanner sc = new Scanner(System.in);
            System.out.print("Insert the person you want to change: ");
            String name = sc.nextLine();

            System.out.print("Insert new name: ");
            String newName = sc.nextLine();

            System.out.print("Insert new phonenumber: ");
            String newPhone = sc.nextLine();

            pSt.setString(1, newName);             //noua parola
            pSt.setString(2, newPhone);
            pSt.setString(3, name);


            // 5. execute a prepared statement
            int rowsUpdated = pSt.executeUpdate();

            // 6. close the objects
            pSt.close();
            conn.close();
        }


        public static void demoDelete() throws ClassNotFoundException, SQLException {

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
            Scanner sc = new Scanner(System.in);
            System.out.print("Find the person you want to delete: ");
            String name = sc.nextLine();
            pSt.setString(1, name);

            // 5. execute a prepared statement
            int rowsDeleted = pSt.executeUpdate();
            System.out.println(rowsDeleted + " rows were deleted.");

            // 6. close the objects
            pSt.close();
            conn.close();
        }
    }



