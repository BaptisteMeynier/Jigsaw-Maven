package com.java.jigsaw.portfolio.api.database;

import com.java.jigsaw.portfolio.model.Portfolio;
import com.java.jigsaw.portfolio.model.PortfolioKey;
import com.java.jigsaw.portfolio.model.enums.Devise;
import com.java.jigsaw.portfolio.spi.PortfolioServicePort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.sql.*;

public class DatabasePortfolioService implements PortfolioServicePort {

    private static final String driver = "org.mariadb.jdbc.Driver";
    private static final String host = "localhost";
    private static final String user = "root";
    private static final String passwd = "";
    private static final String url = String.format("jdbc:mariadb://%s:%s/MavenJigsaw?user=%s&password=%s", host, user, passwd);
    private static final String query = "SELECT code, amount, devise, manager  FROM Portfolio";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Impossible to load driver ");
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
        final String sqlQuery = String.format("%s ORDER BY id ASC LIMIT %i OFFSET %i", totalReturnedValue, offset);
        return fetchPortfolios(sqlQuery);
    }


    @Override
    public Optional<Portfolio> getPortfolio(PortfolioKey key) {
        final String sqlQuery = String.format("%s WHERE CODE=%s", key.getCode());
        List<Portfolio> portfolios = fetchPortfolios(sqlQuery);
        return (!portfolios.isEmpty()) ? Optional.of(portfolios.get(0)) : Optional.empty();
    }

    @Override
    public int countPortfolio() {
        return fetchPortfolios(query).size();
    }


    public List<Portfolio> fetchPortfolios(final String sqlQuery) {
        Optional<Connection> connection = getConnection();
        List<Portfolio> portfolios = Collections.emptyList();
        if (connection.isPresent()) {
            try (final Connection conn = connection.get();
                 final Statement stmt = conn.createStatement();
                 final ResultSet rs = stmt.executeQuery(sqlQuery)
            ) {
                while (rs.next()) {
                    String code = rs.getString("code");
                    int amount = rs.getInt("amount");
                    int deviseRank = rs.getInt("devise");
                    Optional<Devise> devise = Arrays.stream(Devise.values()).filter(dev -> dev.ordinal() == deviseRank).findFirst();
                    String manager = rs.getString("manager");
                    portfolios.add(
                            Portfolio.builder()
                                    .setKey(new PortfolioKey(code))
                                    .setAmount(amount)
                                    .setDevise(devise.orElse(Devise.NONE))
                                    .setManager(manager));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return portfolios;
    }

}
