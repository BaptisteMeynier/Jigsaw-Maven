package com.java.jigsaw.portfolio.api.database;

import com.java.jigsaw.portfolio.model.Portfolio;
import com.java.jigsaw.portfolio.model.PortfolioKey;
import com.java.jigsaw.portfolio.model.enums.Devise;
import com.java.jigsaw.portfolio.spi.PortfolioServicePort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.sql.*;

public class DatabasePortfolioService implements PortfolioServicePort {

    private static final String host = "localhost";
    private static final String user = "root";
    private static final String passwd = "";

    static private Optional<Connection> getConnection() {
        Optional<Connection> conn = Optional.empty();
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            final String url = String.format("jdbc:mariadb://%s:%s/MavenJigsaw?user=%s&password=%s", host, user, passwd);

            conn = Optional.of(DriverManager.getConnection(url));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
        Optional<Connection> connection = getConnection();
        List<Portfolio> portfolios = Collections.emptyList();
        if (connection.isPresent()) {
            try (final Connection conn = connection.get()) {
                Statement stmt = conn.createStatement();
                ResultSet rs;
                rs = stmt.executeQuery("SELECT code, amount, devise, manager  FROM Portfolio");
                while (rs.next()) {
                    String code = rs.getString("code");
                    int amount = rs.getInt("amount");
                    int devise = rs.getInt("devise");
                    String manager = rs.getString("manager");
                    portfolios.add(
                            Portfolio.builder()
                            .setKey(new PortfolioKey(code))
                                    .setAmount(amount)
                                    .setDevise(Devise.valueOf(devise))
                                    .setManager(manager));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return portfolios.subList(offset, offset + totalReturnedValue);
    }

    @Override
    public Optional<Portfolio> getPortfolio(PortfolioKey key) {
        return portfolios.stream().filter(ptf -> ptf.equals(key)).findFirst();
    }

    @Override
    public int countPortfolio() {
        return 0;
    }
}
