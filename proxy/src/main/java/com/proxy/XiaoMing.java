package com.proxy;

/**
 *  ้ๆไปฃ็
 */

public class XiaoMing {
    public static void main(String[] args) {
        PerfumeFactory factory = new PerfumeFactory();
        XiaoHongSellProxy proxy = new XiaoHongSellProxy(factory);
        proxy.sellPerfume(1999.99);
    }
}