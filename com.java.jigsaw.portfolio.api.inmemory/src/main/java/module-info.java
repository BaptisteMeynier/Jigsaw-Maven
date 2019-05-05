module com.java.jigsaw.portfolio.api.inmemory {
    requires transitive com.java.jigsaw.portfolio.spi;
    provides com.java.jigsaw.portfolio.spi with com.java.jigsaw.portfolio.api.InMemoryPortfolioService;
}