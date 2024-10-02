package org.example;

import java.sql.*;
import java.text.DecimalFormat;

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
    static Statement statement;

    static {
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        getSubscriptions();
    }


    public static void getCourses() {
        try (ResultSet resultSet = statement.executeQuery("SELECT * FROM courses")) {
            System.out.printf("|%-3s|%-34s|%-10s|%-12s|%-78s|%-11s|%-15s|%-8s|%-15s|%n", "id", "courseName", "duration",
                    "type", "description", "teacher_id", "students_count", "price", "price_per_hour");
            System.out.println("--------------------------------------------------------------------------------------" +
                    "-------------------------------------------------------------------------------------------------" +
                    "-------------");

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

    public static void getPurchaseList() {
        try (ResultSet resultSet = statement.executeQuery("SELECT * FROM purchaseList")) {
            System.out.printf("|%-30s|%-38s|%-10s|%-20s|%n", "Student name", "Course name", "Price",
                    "Subscription date");
            System.out.println("--------------------------------------------------------------------" +
                    "-----------------------------------");

            while (resultSet.next()) {
                System.out.printf("|%-30s|%-38s|%-10d|%-20s|%n",
                        resultSet.getString("student_name"), resultSet.getString("course_name"),
                        resultSet.getInt("price"), resultSet.getString("subscription_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getStudents() {
        try (ResultSet resultSet = statement.executeQuery("SELECT * FROM students")) {
            System.out.printf("|%-5s|%-22s|%-4s|%-20s|%n", "id", "Student name", "Age",
                    "Registration date");
            System.out.println("--------------------------------------------------------");

            while (resultSet.next()) {
                System.out.printf("|%-5d|%-22s|%-4d|%-20s|%n",
                        resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("age"), resultSet.getString("registration_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getSubscriptions() {
        try (ResultSet resultSet = statement.executeQuery("SELECT * FROM subscriptions")) {
            System.out.printf("|%-15s|%-15s|%-20s|%n", "Student id", "Course id", "Subscription date");
            System.out.println("------------------------------------------------------");

            while (resultSet.next()) {
                System.out.printf("|%-15d|%-15d|%-20s|%n",
                        resultSet.getInt("student_id"), resultSet.getInt("course_id"),
                        resultSet.getString("subscription_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}