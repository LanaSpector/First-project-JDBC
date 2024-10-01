package org.example;

import java.sql.*;

/*
 - вывести инф.из ост.таблиц
 - вывод сделать красивым и читаемым
 - каждый вывод таблицы в своем методе
 - рефакторинг: try with recourses
 - разобраться с dockerCompose file (атрибуты, где их взять, dockerHub)
   */
public class Main {
    static String url = "jdbc:mysql://localhost:3306/top";
    static String user = "root";
    static String password = "root";
    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {
        getCourses();
    }

    public static void getCourses() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM courses")) {
            System.out.printf("|%-3s|%-34s|%-10s|%-12s|%-78s|%-11s|%-15s|%-8s|%-15s|%n", "id", "courseName", "duration",
                    "type", "description", "teacher_id", "students_count", "price", "price_per_hour");

            while (resultSet.next()) {
                System.out.printf("|%-3s|%-34s|%-10s|%-12s|%-78s|%-11s|%-15s|%-8s|%-15s|%n",
                        resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("duration"), resultSet.getString("type"),
                        resultSet.getString("description"), resultSet.getInt("teacher_id"),
                        resultSet.getInt("students_count"), resultSet.getInt("price"),
                        resultSet.getInt("price_per_hour"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}