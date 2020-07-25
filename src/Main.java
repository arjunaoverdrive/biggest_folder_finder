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
            if (i > 3) break;
        }
        String sizeUnit = null;
        for (int i = 0; folderSize / Math.pow(BYTE_FACTOR, i) > 1; i++) {
            if (i == 0) sizeUnit = "B";
            else if (i == 1) sizeUnit = "Кб";
            else if (i == 2) sizeUnit = "Мб";
            else if (i >= 3) {
                sizeUnit = "Гб";
                break;
            }
        }
        return String.format("%s%s", size, sizeUnit);
    }
}
