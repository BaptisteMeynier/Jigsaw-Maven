package com.java.jigsaw.portfolio.spi;

import com.java.jigsaw.portfolio.model.Portfolio;
import com.java.jigsaw.portfolio.model.PortfolioKey;

import java.util.List;
import java.util.Optional;

public interface PortfolioServicePort {

    void printServiceName();

    List<Portfolio> getPortfolios(int offset, int totalReturnedValue);

    Optional<Portfolio> getPortfolio(final PortfolioKey key);

    int countPortfolio();
}
