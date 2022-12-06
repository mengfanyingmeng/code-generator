package com.dynamicProxy;

import com.dynamicProxy.perfume.PerfumeFactory1;
import com.dynamicProxy.perfume.SellPerfume1;
import com.dynamicProxy.proxyFactory.SellProxyFactory;
import com.dynamicProxy.redWine.RedWineFactory;
import com.dynamicProxy.redWine.SellWine;


import java.lang.reflect.Proxy;

/**
 * jdk 动态代理
 */

public class XiaoMing {
    public static void main(String[] args) {
        PerfumeFactory1 chanelFactory = new PerfumeFactory1();
        SellProxyFactory sellProxyFactory = new SellProxyFactory(chanelFactory);
        SellPerfume1 sellPerfumeProxy = (SellPerfume1) Proxy.newProxyInstance(chanelFactory.getClass().getClassLoader(),
                chanelFactory.getClass().getInterfaces(),
                sellProxyFactory);
        sellPerfumeProxy.sellPerfume(1999.99);

        System.out.println("/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/");
        // 实例化一个红酒销售商
        RedWineFactory redWineFactory = new RedWineFactory();
        // 实例化代理工厂，传入红酒销售商引用控制对其的访问
        SellProxyFactory sellProxyFactory1 = new SellProxyFactory(redWineFactory);
        // 实例化代理对象，该对象可以代理售卖红酒
        SellWine sellWineProxy = (SellWine) Proxy.newProxyInstance(redWineFactory.getClass().getClassLoader(),
                redWineFactory.getClass().getInterfaces(),
                sellProxyFactory1);
        // 代理售卖红酒
        sellWineProxy.sellWine(1999.99);
    }
}
