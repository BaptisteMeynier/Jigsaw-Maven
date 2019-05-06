module com.java.jigsaw.portfolio.api.database {
    requires java.sql;
    requires com.java.jigsaw.portfolio.spi;
    provides com.java.jigsaw.portfolio.spi.PortfolioServicePort with DatabasePortfolioService;
}