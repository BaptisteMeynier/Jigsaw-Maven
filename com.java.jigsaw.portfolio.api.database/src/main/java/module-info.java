module com.java.jigsaw.portfolio.api.database {
    requires transitive java.sql;
    //requires org.mariadb.jdbc;
    requires transitive com.java.jigsaw.portfolio.spi;
    provides com.java.jigsaw.portfolio.spi.PortfolioServicePort with com.java.jigsaw.portfolio.api.database.DatabasePortfolioService;
    exports com.java.jigsaw.portfolio.api.database;
}