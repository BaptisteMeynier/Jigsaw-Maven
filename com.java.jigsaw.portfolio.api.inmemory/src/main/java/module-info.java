import com.java.jigsaw.portfolio.api.inmemory.InMemoryPortfolioService;

module com.java.jigsaw.portfolio.api.inmemory {
    requires com.java.jigsaw.portfolio.spi;
    provides com.java.jigsaw.portfolio.spi.PortfolioServicePort with InMemoryPortfolioService;
}