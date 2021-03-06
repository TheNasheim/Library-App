package application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.*;

public class FileUtility {

    public static void saveObject(String filename, Object o, StandardOpenOption... option) {
        Path path = Paths.get(filename);
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path, option))) {
            out.writeObject(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object loadObject(String filename) {
        Path path = Paths.get(filename);
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
            String srt = "";
            return in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
