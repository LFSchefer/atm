package co.simplon.atm.persistance;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static co.simplon.atm.ui.UserInterface.internalError;

public class FileIO {

    public static String returnMatchingLine(String line, String pathToFile, String error) {
        var path = fileExist(pathToFile,error);
        if (path != null) {
            try {
                Stream<String> lines = Files.lines(path);
                return lines.filter(l -> Objects.equals(l.split(",")[0], line)).toList().getFirst();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static Set<String> getAccounts(String pathToFile, String error)  {
        var path = fileExist(pathToFile,error);
        if (path != null) {
            try {
                Stream<String> lines = Files.lines(path);
                return lines.collect(Collectors.toSet());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static Integer readLastLine(String pathToFile, String error) {
        var path = fileExist(pathToFile,error);
        if (path != null) {
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

    public static void writeFile(Map<String,String> accounts, String path) {
        String file = accounts.keySet().stream().map( key -> key + "," + accounts.get(key))
                .collect(Collectors.joining(System.lineSeparator()));
        try {
            Files.write(Paths.get(path), file.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeNewLine(Integer value, String path) {
        writeNewLine(String.valueOf(value), path);
    }

    public static void writeNewLine(String msg, String path) {
        String newLine = System.lineSeparator() + msg;
        try {
            Files.write(Paths.get(path), newLine.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path fileExist(String pathToFile, String error) {
        Path path = Path.of(pathToFile);
        File file = new File(path.toUri());
        var exist = file.exists();
        if (!exist) {
            internalError(error);
            return null;
        }
        return path;
    }
}
