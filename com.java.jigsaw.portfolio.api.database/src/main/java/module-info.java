module com.java.jigsaw.portfolio.api.database {
    requires java.sql;
    uses org.mariadb.jdbc;
    requires com.java.jigsaw.portfolio.spi;
    provides com.java.jigsaw.portfolio.spi.PortfolioServicePort with com.java.jigsaw.portfolio.api.database.DatabasePortfolioService;
}