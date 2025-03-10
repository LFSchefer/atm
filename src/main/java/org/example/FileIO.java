package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class FileIO {

    public static Integer readLastLine(String pathToFile, String error) {
        Path path = Path.of(pathToFile);
        File file = new File(path.toUri());
        var exist = file.exists();
        if (!exist) {
            System.out.println(error);
        } else {
            try {
                Stream<String> lines = Files.lines(path);
                Integer currentBalance = lines.map(Integer::valueOf).toList().getLast();
                return currentBalance;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static void writeNewLine(Integer value, String path) {
        String newLine = System.lineSeparator() + String.valueOf(value);
        try {
            Files.write(Paths.get(path), newLine.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
