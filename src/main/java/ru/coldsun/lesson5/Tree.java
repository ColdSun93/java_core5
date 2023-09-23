package ru.coldsun.lesson5;

import java.io.File;

public class Tree {

    public static void main(String[] args) {

        print(new File("."), "", true);

    }

    /**
     * TODO: Доработать метод print, необходимо распечатывать директории и файлы
     * @param file
     * @param indent
     * @param isLast
     */
    public static void print(File file, String indent, boolean isLast){
        System.out.print(indent);
        if (isLast){
            System.out.print("└─");
            indent += "  ";
        }
        else{
            System.out.print("├─");
            indent += "│ ";
        }

        if (file.isDirectory()) {
            System.out.println(file.getName() + "/");
        } else {
            System.out.println(" /" + file.getName());
            return;
        }
        //System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null)
            return;

        int subDirTotal = 0;
        int subFileTotal =0;
        for (int i = 0; i < files.length; i++){
            if (files[i].isDirectory())
                subDirTotal++;
            else {
                subFileTotal++;
            }
        }

        int subDirCounter = 0;
        int subFileCounter = 0;
        for (File value : files) {
            if (value.isDirectory()) {
                print(value, indent, subDirCounter == subDirTotal - 1 && subFileCounter == subFileTotal);
                subDirCounter++;
            } else {
                print(value, indent, subFileCounter == subFileTotal - 1 && subDirCounter == subDirTotal);
                subFileCounter++;
            }

        }

    }

}
