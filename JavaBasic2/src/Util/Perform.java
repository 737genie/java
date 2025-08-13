package Util;

public class Perform {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int iterations = 100000;

        // 1. String의 '+' 연산 (매우 비효율적)
        long startTime1 = System.currentTimeMillis();
        String str = "";
        for (int i = 0; i < iterations; i++) {
            str += "a";
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("String (+): " + (endTime1 - startTime1) + "ms  <-- (커피 한 잔 끓여와도 될 시간)");

        // 2. StringBuffer의 append() (안전하고 빠름)
        long startTime2 = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sbf.append("a");
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("StringBuffer: " + (endTime2 - startTime2) + "ms <-- (안전한데도 눈 깜짝할 사이!)");

        // 3. StringBuilder의 append() (가장 빠름)
        long startTime3 = System.currentTimeMillis();
        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sbd.append("a");
        }
        long endTime3 = System.currentTimeMillis();
        System.out.println("StringBuilder: " + (endTime3 - startTime3) + "ms <-- (가장 빠름! 역시 자물쇠가 없어야...)");
	}

}
