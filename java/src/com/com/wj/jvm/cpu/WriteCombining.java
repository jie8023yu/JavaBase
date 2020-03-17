package com.com.wj.jvm.cpu;

/**
 * 测试合并写
 * WCBuffer的速度比L1Cache还要快，非常稀缺，容量一般在4个字节左右
 *
 * 马士兵老师写的是3个3个测试，caseOne和caseTwo的测试差距在2倍左右
 * 而我测试的大致差不多，如果设置成4个4个测试，差距大概如下，有一定提升，但也不是绝对提升，不知道是不是电脑的原因
 * 可以查看同包的Test类
 * 1 SingleLoop duration (ns) = 7656343100
 * 1 SplitLoop  duration (ns) = 5936282200
 * 2 SingleLoop duration (ns) = 7688746000
 * 2 SplitLoop  duration (ns) = 6086954300
 * 3 SingleLoop duration (ns) = 6279152000
 * 3 SplitLoop  duration (ns) = 6065263400
 *
 */
public class WriteCombining {


    private static final int ITERATIONS = Integer.MAX_VALUE;

    private static final int ITEMS = 1 << 24;
    private static final int MASK = ITEMS - 1;

    private static final byte[] arrayA = new byte[ITEMS];
    private static final byte[] arrayB = new byte[ITEMS];
    private static final byte[] arrayC = new byte[ITEMS];
    private static final byte[] arrayD = new byte[ITEMS];
    private static final byte[] arrayE = new byte[ITEMS];
    private static final byte[] arrayF = new byte[ITEMS];

    public static void main(String[] args) {
        for (int i = 1 ; i <= 3 ; i++) {
            System.out.println(i + "SingleLoop duration (ns) = " + runCaseOne());
            System.out.println(i + "SplitLoop duration (ns) =  " + runCaseTwo());
        }
    }

    public static long runCaseOne() {
        long start = System.nanoTime();
        int i = ITERATIONS;
        while (--i != 0) {
            int solt = i & MASK;
            byte b = (byte) i;
            arrayA[solt] = b;
            arrayB[solt] = b;
            arrayC[solt] = b;
            arrayD[solt] = b;
            arrayE[solt] = b;
            arrayF[solt] = b;
        }
        return (System.nanoTime() - start);
    }

    public static long runCaseTwo() {
        long start = System.nanoTime();
        int i = ITERATIONS;
        while (--i != 0) {
            int solt = i & MASK;
            byte b = (byte) i;
            arrayA[solt] = b;
            arrayB[solt] = b;
            arrayC[solt] = b;
        }
        i = ITERATIONS;
        while (--i != 0) {
            int solt = i & MASK;
            byte b = (byte) i;
            arrayD[solt] = b;
            arrayE[solt] = b;
            arrayF[solt] = b;
        }
        return (System.nanoTime() - start);
    }

}
