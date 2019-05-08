package com.java.jigsaw.portfolio.model;

import com.java.jigsaw.portfolio.model.enums.Devise;

import java.util.Objects;

public class Portfolio {
    private PortfolioKey key;
    private int amount;
    private Devise devise;
    private String manager;

    private Portfolio() {
    }

    private Portfolio(PortfolioKey key, int amount, Devise devise, String manager) {
        this.key = key;
        this.amount = amount;
        this.devise = devise;
        this.manager = manager;
    }

    public PortfolioKey getKey() {
        return key;
    }

    public void setKey(PortfolioKey key) {
        this.key = key;
    }

    public int getAmount() {
        return amount;
    }

    public Devise getDevise() {
        return devise;
    }

    public String getManager() {
        return manager;
    }

    public static InitStep builder() {
        return new Builder();
    }

    public interface InitStep {
        AmountStep setKey(PortfolioKey key);
        //  DeviseStep setAmount(int amount);
    }

    public interface AmountStep {
        DeviseStep setAmount(int amount);
    }

    public interface DeviseStep {
        ManagerStep setDevise(Devise devise);
    }

    public interface ManagerStep {
        Portfolio setManager(String manager);
    }

    private static class Builder implements InitStep, AmountStep, DeviseStep, ManagerStep {

        private PortfolioKey key;
        private int amount;
        private Devise devise;

        @Override
        public AmountStep setKey(PortfolioKey key) {
            this.key = key;
            return this;
        }

        @Override
        public DeviseStep setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        @Override
        public ManagerStep setDevise(Devise devise) {
            this.devise = devise;
            return this;
        }

        @Override
        public Portfolio setManager(String manager) {
            return new Portfolio(key, amount, devise, manager);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portfolio portfolio = (Portfolio) o;
        return amount == portfolio.amount &&
                Objects.equals(key, portfolio.key) &&
                devise == portfolio.devise &&
                Objects.equals(manager, portfolio.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, amount, devise, manager);
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "key=" + key +
                ", amount=" + amount +
                ", devise=" + devise +
                ", manager='" + manager + '\'' +
                '}';
    }
}


