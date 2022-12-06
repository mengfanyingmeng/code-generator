package com.cglib;

/**
 * cglib 动态代理
 */
public class XiaoMing {
    public static void main(String[] args) {
        SellProxyFactory sellProxyFactory = new SellProxyFactory();
        // 获取一个代理实例
        PerfumeFactory2 proxyInstance =
                (PerfumeFactory2) sellProxyFactory.getProxyInstance(new PerfumeFactory2());
        // 创建代理类
        proxyInstance.sellPerfume(199);

        SellProxyFactory sellProxyFactory1 = new SellProxyFactory();
        // 获取一个代理实例
            RedWineFactory2 proxyInstance1 =
                (RedWineFactory2) sellProxyFactory1.getProxyInstance(new RedWineFactory2());
        // 创建代理类
        proxyInstance1.sellWine(999);
    }
}

