import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        try {
            String folderPath = "C:\\Users\\Igor\\Desktop";
            long sizeLimit = 1024 *1024 * 50;
            File file = new File(folderPath);
            Node root = new Node(file);
            long start = System.currentTimeMillis();

            FolderSizeCalculator calculator =
                    new FolderSizeCalculator(root);
            ForkJoinPool pool = new ForkJoinPool();
            pool.invoke(calculator);

            System.out.println(root);
//            System.out.println(getHumanReadableSize(size));
//            System.out.println(getFromHumanReadableSize(getHumanReadableSize(size)));
            //System.out.println(getFolderSize(file));
            long duration = System.currentTimeMillis() - start;
            System.out.println(duration + " ms");
            System.out.println(SizeCalculator.getFromHumanReadableSize("250M"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//    public static long getFolderSize(File folder) throws NullPointerException {
//        if (folder.isFile()) {
//            return folder.length();
//        }
//        File[] files = folder.listFiles();
//        long sum = Stream.of(files).mapToLong(File::length).sum();
//
//        return sum;
//    }
}
