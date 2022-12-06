package com.dynamicProxy.perfume;

import com.proxy.SellPerfume;

public class PerfumeFactory1 implements SellPerfume1 {
    @Override
    public void sellPerfume(double price) {
        System.out.println("成功购买香奈儿品牌的香水，价格是：" + price + "元");
    }
}
