package helper;

public class DoubleClick {

    private static long previous;

    public static Boolean isDoubleClick() {

        long now = System.currentTimeMillis();
        long diff = now - previous;
        if (diff < 300) {
            return true;
        }
        previous = now;

        return false;

    }

}
