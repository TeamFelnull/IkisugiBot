package red.felnull.ikisugibot;

import org.apache.commons.io.IOUtils;
import red.felnull.ikisugibot.util.FileLoader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

public class DataDownload {
    public static void init() {
        FileLoader.createFolder(OptionConfig.DATA_PATH);
        dwonloadFile(OptionConfig.DATA_PATH.resolve("jpn.traineddata"), "https://github.com/tesseract-ocr/tessdata/blob/master/jpn.traineddata?raw=true");
        dwonloadFile(OptionConfig.DATA_PATH.resolve("eng.traineddata"), "https://github.com/tesseract-ocr/tessdata/blob/master/eng.traineddata?raw=true");
    }

    private static void dwonloadFile(Path path, String url) {
        if (path.toFile().exists()) {
            return;
        }
        try {
            System.out.println(path.toFile().getName() + "をダウンロードしています");
            BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            FileLoader.fileBytesWriter(IOUtils.toByteArray(in), path);
            System.out.println(path.toFile().getName() + "のダウンロードが終わりました");
        } catch (IOException e) {
            System.out.println(path.toFile().getName() + "のダウンロードに失敗しました");
        }
    }
}
