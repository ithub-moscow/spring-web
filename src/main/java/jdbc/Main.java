package jdbc;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

//        Statement statement = connection.createStatement();
//        boolean result = statement.execute(
//                "create table teachers (id int, name varchar)");
//        System.out.println("Executed: " + result);
        insertData(connection);
        selectData(connection);
    }

    private static void selectData(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from teachers");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println(String.format("Id: %s, Name: %s", id, name));
        }
    }

    public static void insertData(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into teachers values (?, ?)");
        statement.setInt(1, 2);
        statement.setString(2, "Teacher2");

        int result = statement.executeUpdate();

        System.out.println("Executed: " + result);
    }
}
