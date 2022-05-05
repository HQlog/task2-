import org.jetbrains.annotations.NotNull;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Find {
    @Option(name = "-r")
    private boolean r = false;
    @Option(name = "-d")
    private String direktory;


    @Argument
    private List<String> arguments = new ArrayList<String>(1); //File

    private void workWithArguments(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (arguments.isEmpty()) {
                throw new IOException("YourArgumentsIsEmpty");
            }

        } catch (Exception CmdLineException) {
            System.err.println(CmdLineException.getMessage());
            parser.printUsage(System.err);
            throw new IllegalArgumentException("");
        }


//передаём имя входного файл
        String fileName = arguments.get(0);
        if (direktory.isEmpty()) throw new IOException();
        if (r) {
            findFiles(direktory, fileName);
        } else {
            findFilesWithoutR(direktory, fileName);
        }


    }

    public static void main(String[] args) throws IOException {

        new Find().workWithArguments("-d /Users/Dima/progect/input input.txt".trim().split(" "));
        new Find().workWithArguments("-r -d /Users/Dima/progect/input/dir f1.txt".trim().split(" "));

    }

    public void isDirektory(@NotNull File dir, String name) {
        if (dir.isDirectory()) {
            if (!dir.exists()) {
                System.out.println(dir + " папка не существует");
                return;
            }
            File[] listFiles = dir.listFiles();
            if (listFiles.length == 0) {
                System.out.println(dir + " не содержит файлов");
            } else {
                for (File f : listFiles) {
                    isDirektory(f, name);
                    if (f.getName().equals(name)) {
                        System.out.println("Файл: " + dir + File.separator + f.getName());
                    }

                }
            }

        }
    }

    private void findFiles(String dir, String name) {
        File file = new File(dir);
        isDirektory(file, name); // рекурсия
    }

    private void findFilesWithoutR(String file, String name) {
        File dir = new File(file);
        if (dir.isDirectory()) {
            if (!dir.exists()) {
                System.out.println(dir + " папка не существует");
                return;
            }
            File[] listFiles = dir.listFiles();
            if (listFiles.length == 0) {
                System.out.println(dir + " не содержит файлов");
            } else {
                for (File f : listFiles) {
                    if (f.getName().equals(name)) {
                        System.out.println("Файл: " + dir + File.separator + f.getName());
                    }

                }
            }

        }
    }

}