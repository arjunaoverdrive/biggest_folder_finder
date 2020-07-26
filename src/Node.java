import java.io.File;
import java.util.ArrayList;

public class Node {

    private File folder;
    private ArrayList<Node> children;
    private long size;
    private int level;
    long sizeLimit = 1024 * 1024 * 50;

    public Node(File folder) {
        this.folder = folder;
        children = new ArrayList<>();
    }

    public File getFolder() {
        return folder;
    }

    public void addChild(Node node) {
        node.setLevel(level + 1);
        children.add(node);
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public int getLevel() {
        return level;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public String toString() {
        String size = SizeCalculator.getHumanReadableSize(getSize());
        StringBuilder builder = new StringBuilder();
        builder.append(folder.getName() + " - " + size + "\n");
        for (Node child : children) {
            if (child.size < sizeLimit) continue;
            else {
                child.getLevel();
                for (int i = 0; i < child.getLevel(); i++) {
                    builder.append("  ");
                }
                builder.append(child.toString());
            }
        }
        return builder.toString();
    }
}
