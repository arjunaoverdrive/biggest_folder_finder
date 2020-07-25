import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try {
            String folderPath = "C:\\Users\\Igor\\Desktop\\Magazines";
            File file = new File(folderPath);
            System.out.println(getFolderSize(file));
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
}
