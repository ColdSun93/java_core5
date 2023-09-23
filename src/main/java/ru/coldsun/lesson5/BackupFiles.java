package ru.coldsun.lesson5;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
/**
* Написать функцию, создающую резервную
*  копию всех файлов в директории
* во вновь созданную папку ./backup
* */

public class BackupFiles {
    public static void main(String[] args) {
        //String pathSource = "./";
        String pathSource = "E:\\study\\Java\\practic\\lesson5\\";
        //String pathBackup = "./backup";
        String pathBackup = "E:\\study\\Java\\practic\\lesson5";

        try {
            createBackup(pathSource, pathBackup);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Основная функция
     *      * @pathSource путь к исходному каталогу
     *      * @pathBackup путь к конечному каталогу + ./backup
     *      * @return результат поиска
     *      * @throws IOException
    * */
    private static void createBackup(String pathSource, String pathBackup) throws IOException {
        // Создаем папку для резервных копий, если ее нет
        File backupDir = new File(pathBackup + "./backup");
        if (!backupDir.isDirectory()) {
            backupDir.mkdir();
            if (!backupDir.isDirectory()) {
                throw new RuntimeException(" Неверный путь к файлу Buckup " + pathBackup);
            }
        }
        // Получаем список файлов в директории
        File sourceDir = new File(pathSource);
        if (!sourceDir.isDirectory() ) {
            throw new RuntimeException("Неверный путь к исходному каталогу " + pathSource);
        }
        File[] filesToBackup = sourceDir.listFiles();

        // Копируем каждый файл в папку с резервными копиями
        if (filesToBackup != null) {
            for (File file : filesToBackup) {
                if (file.isFile()) {
                    //добавим файл .gitignore в качестве исключения
                    if (!file.getName().equals(".gitignore")){
                        Files.copy(file.toPath(), new File(backupDir.getPath() + "/" + file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
        }
    }
}
