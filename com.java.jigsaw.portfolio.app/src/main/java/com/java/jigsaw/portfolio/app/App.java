package com.java.jigsaw.portfolio.app;

import com.java.jigsaw.portfolio.api.database.DatabasePortfolioService;
import com.java.jigsaw.portfolio.model.Portfolio;
import com.java.jigsaw.portfolio.model.PortfolioKey;
import com.java.jigsaw.portfolio.spi.PortfolioServicePort;

import java.util.Optional;

/**
 * Hello world!
 *
 */
public class App 
{
    private static PortfolioServicePort service = new DatabasePortfolioService();
    public static void main( String[] args )
    {
        System.out.println( "Welcome to the Portfolio CLI" );

        System.out.println(String.format("There are %i Portfolio(s)", service.countPortfolio()));
        findPtf("PTF0000");
        findPtf("PTF0001");
        findPtf("XXXXXXX");
        findPtf("PTF9999");

        service.getPortfolios(0,9999).stream().forEach(ptf-> System.out.println(ptf));
    }

    private static void findPtf(final String ptfCode){
        System.out.println(String.format("Getting Portfolio %s:",ptfCode));
        Optional<Portfolio> portfolio = service.getPortfolio(new PortfolioKey(ptfCode));
        if(portfolio.isPresent()) {
            System.out.println(portfolio.get());
        }else {
            System.out.println(String.format("Portfolio %s does not exist",ptfCode));
        }
    }
}
