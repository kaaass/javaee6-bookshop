package net.kaaass.bookshop.util;

import java.io.*;

public class FileUtils {

    public static String readAll(String filename) throws IOException {
        FileReader fr = new FileReader(filename);
        int ch;
        StringBuilder stringBuilder = new StringBuilder();
        while ((ch = fr.read()) != -1) {
            stringBuilder.append((char) ch);
        }
        fr.close();
        return stringBuilder.toString();
    }

    public static void saveToFile(InputStream uploadedInputStream, File file) {
        try {
            OutputStream out = null;
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(file);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
