package bloomFilter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 位图的部分源码
 */
public class BitSet1 {

    private final static int ADDRESS_BITS_PER_WORD = 6;
    private final static int BITS_PER_WORD = 1 << ADDRESS_BITS_PER_WORD; // 64
    private final static int BIT_INDEX_MASK = BITS_PER_WORD - 1;

    private static long[] words;

    //words是否有用户指定。如果是这样的话，我们假设用户知道他在做什么，并更加努力地保存它。
    //被transient修饰的变量不参与序列化和反序列化
    private transient boolean sizeIsSticky = false;

    public BitSet1() {
        //初始化 words = 1，sizeIsSticky = false
        initWords(BITS_PER_WORD);
        sizeIsSticky = false;
    }

    public BitSet1(int nbits) {
        // nbits can't be negative; size 0 is OK
        if (nbits < 0)
            throw new NegativeArraySizeException("nbits < 0: " + nbits);

        initWords(nbits);
        sizeIsSticky = true;
    }

    //假设以64bit为单位，该方法计算需要几个单位
    private static void initWords(int nbits) {
        words = new long[wordIndex(nbits-1) + 1];
    }

    //该方法计算 n/64
    private static int wordIndex(int bitIndex) {
        return bitIndex >> ADDRESS_BITS_PER_WORD;
    }

    public void set(int bitIndex) {
        if (bitIndex < 0)
            throw new IndexOutOfBoundsException("bitIndex < 0: " + bitIndex);

        int wordIndex = wordIndex(bitIndex);

        words[wordIndex] |= (1L << bitIndex); // Restores invariants

    }

    public static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis());
        Runnable runnable=()->{
            for (long i = 0; i < 1000000000; i++) {
                num.getAndAdd(1);
            }
            System.out.println(Thread.currentThread().getName()+"执行结束!");
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("num = " + num);

    }
}
