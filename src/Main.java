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
            if (i == 0) sizeUnit = "байт";
            else if (i == 1) sizeUnit = "Кб";
            else if (i == 2) sizeUnit = "Мб";
            else if (i == 3) {
                sizeUnit = "Гб"; }
            else sizeUnit = "Тб";
            break;
        }
        return String.format("%s%s", size, sizeUnit);
    }

    private static long getFromHumanReadableSize(String folderString) {
        final int BYTE_FACTOR = 1024;
        long size = Long.valueOf(folderString.replaceAll("[^0-9]",""));
        if (folderString.contains("байт")){
            return size;
        }
        else if (folderString.contains("Кб")){
            return size * BYTE_FACTOR;
        }
        else if (folderString.contains("Мб")){
            return (long) (size * Math.pow(BYTE_FACTOR,2));
        }
        else if (folderString.contains("Гб")){
            return (long) (size * Math.pow(BYTE_FACTOR, 3));
        }
        else return (long) (size * Math.pow(BYTE_FACTOR, 4));
    }
}
