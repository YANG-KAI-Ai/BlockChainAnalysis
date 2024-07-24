package com.bit.BitcoinAnalysis.entity;

// 定义一个内部类来表示每个币种的名称和价格
public class CoinPrice {
    private String name;
    private Long price;

    public CoinPrice(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
