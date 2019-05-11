package com.java.jigsaw.portfolio.app;

import com.java.jigsaw.portfolio.spi.PortfolioServicePort;

import java.util.NoSuchElementException;
import java.util.ServiceLoader;

public class PortfolioProvider {
    private static PortfolioProvider provider;

    private ServiceLoader<PortfolioServicePort> loader;

    private PortfolioProvider() {
        loader = ServiceLoader.load(PortfolioServicePort.class);
    }

    public static PortfolioProvider getInstance() {
        if(provider == null) {
            provider = new PortfolioProvider();
        }
        return provider;
    }

    public PortfolioServicePort serviceImpl() {
        PortfolioServicePort service = loader.iterator().next();

        if(service != null) {
            return service;
        } else {
            throw new NoSuchElementException("No implementation for GreetingsProvider");
        }
    }

}
