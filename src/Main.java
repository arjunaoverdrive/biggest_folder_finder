import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try {
            String folderPath = "C:\\Users\\Igor\\Desktop\\Magazines";
            File file = new File(folderPath);
            long start = System.currentTimeMillis();

            FolderSizeCalculator calculator =
                    new FolderSizeCalculator(file);
            ForkJoinPool pool = new ForkJoinPool();
            long size = pool.invoke(calculator);
            System.out.println(getHumanReadableSize(size));
            System.out.println(getFromHumanReadableSize(getHumanReadableSize(size)));
            //System.out.println(getFolderSize(file));

            long duration = System.currentTimeMillis() - start;
            System.out.println(duration + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long getFolderSize(File folder) throws NullPointerException{
        if (folder.isFile()) {
            return folder.length();
        }
        File[] files = folder.listFiles();
        long sum = Stream.of(files).mapToLong(File::length).sum();

        return sum;
    }

    public static String getHumanReadableSize(float folderSize){
        final int BYTE_FACTOR = 1024;
        float size = 0;
        for (int i = 0; folderSize / Math.pow(BYTE_FACTOR, i) >= 1; i++) {
            size = Math.round((float) (folderSize / Math.pow(BYTE_FACTOR, i)));
            if (i > 4) break;
        }
        String sizeUnit = null;
        String[] cIUnits = {"B", "K", "M", "G", "T"};
        for (int i = 0; folderSize / Math.pow(BYTE_FACTOR, i) > 1; i++) {
            sizeUnit = cIUnits[0];
        }
        return String.format("%s%s", size, sizeUnit);
    }

    private static long getFromHumanReadableSize(String folderString) {
        final int BYTE_FACTOR = 1024;
        long size = Long.valueOf(folderString.replaceAll("[^0-9]",""));
        if (folderString.contains("B")){
            return size;
        }
        else if (folderString.contains("K")){
            return size * BYTE_FACTOR;
        }
        else if (folderString.contains("M")){
            return (long) (size * Math.pow(BYTE_FACTOR,2));
        }
        else if (folderString.contains("G")){
            return (long) (size * Math.pow(BYTE_FACTOR, 3));
        }
        else return (long) (size * Math.pow(BYTE_FACTOR, 4));
    }
}
