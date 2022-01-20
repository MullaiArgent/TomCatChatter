package datamanagement;

import java.sql.*;


public class JDBC {

    String username = "postgres";
    String password = "@TeslaPostgresql2000";

    public ResultSet dql(String query) throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/chat";
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        con.close();
        return rs;
    }

    public void dml(String query) throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://localhost:5432/chat";
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        st.executeUpdate(query);
        con.close();
    }
    public void addUser(String query) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost/tomcatusers";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, "tom", "testpassword");
        Statement st = con.createStatement();
        st.executeUpdate(query);
        con.close();
    }
}


