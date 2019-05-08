package com.java.jigsaw.portfolio.api.database;

import com.java.jigsaw.portfolio.model.Portfolio;
import com.java.jigsaw.portfolio.model.PortfolioKey;
import com.java.jigsaw.portfolio.model.enums.Devise;
import com.java.jigsaw.portfolio.spi.PortfolioServicePort;

import java.util.*;

import java.sql.*;

public class DatabasePortfolioService implements PortfolioServicePort {

    private static final String driver = "org.mariadb.jdbc.Driver";
    private static final String host = "localhost";
    private static final String port = "3306";
    private static final String user = "root";
    private static final String passwd = "";
    private static final String url = String.format("jdbc:mariadb://%s:%s/Portfolio?user=%s&password=%s", host, port, user, passwd);
    private static final String query = "SELECT CODE,AMOUNT,DEVISE,MANAGER FROM PORTFOLIO";
    private static final String count_query = "SELECT count(CODE) as Total FROM PORTFOLIO";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible to load driver");
            e.printStackTrace();
        }
    }

    static private Optional<Connection> getConnection() {
        Optional<Connection> conn = Optional.empty();
        try {
            conn = Optional.of(DriverManager.getConnection(url));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    @Override
    public void printServiceName() {
        System.out.println(DatabasePortfolioService.class.getName());
    }

    @Override
    public List<Portfolio> getPortfolios(int offset, int totalReturnedValue) {
        final String sqlQuery = String.format("%s ORDER BY id ASC LIMIT %d OFFSET %d", query, totalReturnedValue, offset);
        return fetchPortfolios(sqlQuery);
    }


    @Override
    public Optional<Portfolio> getPortfolio(PortfolioKey key) {
        final String sqlQuery = String.format("%s WHERE CODE='%s'", query, key.getCode());
        List<Portfolio> portfolios = fetchPortfolios(sqlQuery);
        return (!portfolios.isEmpty()) ? Optional.of(portfolios.get(0)) : Optional.empty();
    }

    @Override
    public int countPortfolio() {
        int total = 0;
        Optional<Connection> connection = getConnection();
        if (connection.isPresent()) {
            try (final Connection conn = connection.get();
                 final Statement stmt = conn.createStatement();
                 final ResultSet rs = stmt.executeQuery(count_query)
            ) {
                rs.next();
                total = Integer.valueOf(rs.getInt("Total"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return total;
    }


    private List<Portfolio> fetchPortfolios(final String sqlQuery) {
        Optional<Connection> connection = getConnection();
        List<Portfolio> portfolios = new ArrayList<>();
        if (connection.isPresent()) {
            try (final Connection conn = connection.get();
                 final Statement stmt = conn.createStatement();
                 final ResultSet rs = stmt.executeQuery(sqlQuery)
            ) {
                while (rs.next()) {
                    String code = rs.getString("code");
                    int amount = rs.getInt("amount");
                    Devise devise = Devise.valueOf(rs.getString("devise"));
                    String manager = rs.getString("manager");
                    portfolios.add(
                            Portfolio.builder()
                                    .setKey(new PortfolioKey(code))
                                    .setAmount(amount)
                                    .setDevise(devise)
                                    .setManager(manager));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return portfolios;
    }

}
