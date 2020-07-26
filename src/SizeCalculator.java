import java.io.File;
import java.util.HashMap;

public class SizeCalculator {
    //    private static char[] sizeMultipliers =
//            {'B', 'K', 'M', 'G', 'T'};
    private static char[] cIUnits = {'B', 'K', 'M', 'G', 'T'};
    final static int BYTE_FACTOR = 1024;

    public static String getHumanReadableSize(float folderSize) {
        float size = 0;
        for (int i = 0; folderSize / Math.pow(BYTE_FACTOR, i) >= 1; i++) {
            size = (float) (folderSize / Math.pow(BYTE_FACTOR, i));
            if (i > 4) break;
        }
        char sizeUnit = 0;

        for (int i = 0; folderSize / Math.pow(BYTE_FACTOR, i) > 1; i++) {
            sizeUnit = cIUnits[i];
        }
        return String.format("%.4f%s", size, sizeUnit);
    }

    public static long getFromHumanReadableSize(String folderString) {
        long size = Long.parseLong(folderString.replaceAll("[^0-9]", ""));
        String multiplier = folderString.replaceAll("[0-9]", "");
        long folderSize = 0;
        for (int i = 0; i < cIUnits.length; i++) {
            if (multiplier.contains(String.valueOf(cIUnits[i])))
                folderSize = (long) (size * Math.pow(BYTE_FACTOR, i));
        }
        return folderSize;
    }
}