import java.io.File;

public class ParametersBag {
    private long limit;
    private String path;

    public ParametersBag(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Введите корректный минимальный размер файла (-l) и путь к папке (-d)");
        }

        limit = 0;
        path = "";
        for (int i = 0; i < 4; i += 2) {
            if (args[i].equals("-l")) limit = SizeCalculator.getFromHumanReadableSize(args[i + 1]);
            else if (args[i].equals("-d")) path = args[i + 1];
        }
        if (limit < 0) throw new IllegalArgumentException("Минимальный размер папки не указан/ указан неверно");

        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("Путь к папке указан неверно!");
        }
    }

    public long getLimit() {
        return limit;
    }

    public String getPath() {
        return path;
    }
}
