package com.java.jigsaw.portfolio.api;

import com.java.jigsaw.portfolio.model.Portfolio;
import com.java.jigsaw.portfolio.model.PortfolioKey;
import com.java.jigsaw.portfolio.model.enums.Devise;
import com.java.jigsaw.portfolio.spi.PortfolioServicePort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryPortfolioService implements PortfolioServicePort {


    private List<Portfolio> portfolios = List.of(
            Portfolio.builder().setKey(new PortfolioKey("PTF0001")).setAmount(100).setDevise(Devise.EURO).setManager("Antoine"),
            Portfolio.builder().setKey(new PortfolioKey("PTF0002")).setAmount(10).setDevise(Devise.EURO).setManager("Antoine"),
            Portfolio.builder().setKey(new PortfolioKey("PTF0003")).setAmount(90).setDevise(Devise.EURO).setManager("Antoine"),
            Portfolio.builder().setKey(new PortfolioKey("PTF0004")).setAmount(110).setDevise(Devise.DOLLAR).setManager("Baptiste"),
            Portfolio.builder().setKey(new PortfolioKey("PTF0004")).setAmount(5).setDevise(Devise.DOLLAR).setManager("Baptiste"),
            Portfolio.builder().setKey(new PortfolioKey("PTF0004")).setAmount(75).setDevise(Devise.DOLLAR).setManager("Baptiste")
    );

    @Override
    public void printServiceName() {
        System.out.println(InMemoryPortfolioService.class.getName());
    }

    @Override
    public List<Portfolio> getPortfolios(int offset, int totalReturnedValue) {
        return portfolios.subList(offset,offset+totalReturnedValue);
    }

    @Override
    public Optional<Portfolio> getPortfolio(PortfolioKey key) {
        return portfolios.stream().filter(ptf->ptf.equals(key)).findFirst();
    }

    @Override
    public int countPortfolio() {
        return 0;
    }
}
