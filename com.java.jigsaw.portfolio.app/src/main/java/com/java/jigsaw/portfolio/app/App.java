package com.java.jigsaw.portfolio.app;

import com.java.jigsaw.portfolio.api.database.DatabasePortfolioService;
import com.java.jigsaw.portfolio.model.Portfolio;
import com.java.jigsaw.portfolio.model.PortfolioKey;
import com.java.jigsaw.portfolio.spi.PortfolioServicePort;

import java.util.Optional;


public class App 
{
    private static PortfolioServicePort service = new DatabasePortfolioService();

    public static void main( String[] args )
    {
        System.out.println( "Welcome to the Portfolio CLI" );

        System.out.println(String.format("There are %d Portfolio(s)", service.countPortfolio()));
        findPtf("PTF0000000");
        findPtf("PTF0000001");
        findPtf("XXXXXXXXXX");
        findPtf("PTF0004000");

        System.out.println("Getting first 10 Portfolios:");
        service.getPortfolios(0,10).stream().forEach(ptf-> System.out.println("    " + ptf));
    }

    private static void findPtf(final String ptfCode){
        System.out.println(String.format("Trying to get Portfolio %s:",ptfCode));
        Optional<Portfolio> portfolio = service.getPortfolio(new PortfolioKey(ptfCode));
        if(portfolio.isPresent()) {
            System.out.println(portfolio.get());
        }else {
            System.out.println(String.format("Portfolio %s does not exist",ptfCode));
        }
    }
}
