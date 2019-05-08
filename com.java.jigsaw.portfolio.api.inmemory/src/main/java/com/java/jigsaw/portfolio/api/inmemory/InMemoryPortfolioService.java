package com.java.jigsaw.portfolio.api.inmemory;

import com.java.jigsaw.portfolio.model.Portfolio;
import com.java.jigsaw.portfolio.model.PortfolioKey;
import com.java.jigsaw.portfolio.model.enums.Devise;
import com.java.jigsaw.portfolio.spi.PortfolioServicePort;

import java.util.List;
import java.util.Optional;

public class InMemoryPortfolioService implements PortfolioServicePort {


    private List<Portfolio> portfolios = List.of(
            Portfolio.builder().setKey(new PortfolioKey("PTF000001")).setAmount(100).setDevise(Devise.EURO).setManager("Antoine"),
            Portfolio.builder().setKey(new PortfolioKey("PTF000002")).setAmount(10).setDevise(Devise.EURO).setManager("Antoine"),
            Portfolio.builder().setKey(new PortfolioKey("PTF000003")).setAmount(90).setDevise(Devise.EURO).setManager("Antoine"),
            Portfolio.builder().setKey(new PortfolioKey("PTF000004")).setAmount(110).setDevise(Devise.DOLLAR).setManager("Baptiste"),
            Portfolio.builder().setKey(new PortfolioKey("PTF000005")).setAmount(5).setDevise(Devise.DOLLAR).setManager("Baptiste"),
            Portfolio.builder().setKey(new PortfolioKey("PTF000006")).setAmount(14).setDevise(Devise.DOLLAR).setManager("Baptiste"),
            Portfolio.builder().setKey(new PortfolioKey("PTF000007")).setAmount(455).setDevise(Devise.DOLLAR).setManager("Baptiste"),
            Portfolio.builder().setKey(new PortfolioKey("PTF000008")).setAmount(900).setDevise(Devise.DOLLAR).setManager("Baptiste"),
            Portfolio.builder().setKey(new PortfolioKey("PTF000009")).setAmount(75).setDevise(Devise.DOLLAR).setManager("Baptiste")
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
        return portfolios.size();
    }
}
