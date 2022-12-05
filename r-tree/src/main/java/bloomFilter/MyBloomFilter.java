package bloomFilter;

import org.jetbrains.annotations.NotNull;

import java.util.BitSet;

/**
 * 基于位图的布隆过滤器
 */
public class MyBloomFilter {
    /**
     * 一个长度为10亿的比特位
     */
    private static final int DEFAULT_SIZE = 256 << 22;

    private static final int[] seeds = {3, 5, 7, 11, 13, 31, 37, 61};

    private static HashFunction[] functions = new HashFunction[seeds.length];

    private static BitSet bitset = new BitSet(DEFAULT_SIZE);

    static {
        for (int i = 0; i < functions.length; i++) {
            functions[i]=new HashFunction(DEFAULT_SIZE,seeds[i]);
        }
    }

    public static void add(String value) {
        if (value != null) {
            for (HashFunction f : functions) {
                //计算 hash 值并修改 bitmap 中相应位置为 true
                bitset.set(f.hash(value), true);
            }
        }
    }

    public static boolean contains(String value){
        if(value==null){
            return false;
        }
        boolean ret=true;

        for (HashFunction f : functions) {
            ret=bitset.get(f.hash(value));
            if(!ret){
                break;
            }
        }
        return ret;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 100000000; i++){
            add(i+"");
        }
        String id1=1234567+"";
        String id2=123456789+"";
        System.out.println(id1+"   "+contains(id1));
        System.out.println(id2+"   "+contains(id2));

    }


    static class HashFunction{

        private int size;
        private int seed;

        public HashFunction(int size, int seed) {
            this.size = size;
            this.seed = seed;
        }

        public int hash(@NotNull String value){
            int result=0;
            int len=value.length();
            for (int i = 0; i < len; i++) {
                result=seed*result+value.charAt(i);
            }
            return (size-1)&result;
            //[(256 << 22)-1]&result == result
        }
    }


}
