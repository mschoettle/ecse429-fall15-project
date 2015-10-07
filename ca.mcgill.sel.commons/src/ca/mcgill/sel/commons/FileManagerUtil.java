package ca.mcgill.sel.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * FileCopyUtil class used to copy or delete files/directories.
 *
 * @author Nishanth
 */
public final class FileManagerUtil {

    /**
     * Prevent to instantiate.
     */
    private FileManagerUtil() {
    }

    /**
     * Helper function used to copy the directory.
     * Recursively calls it on all sub files and directories.
     *
     * @param source - The source directory/folder.
     * @param target - THe target directory/ folder.
     * @return - true or false.
     * @throws IOException - If anything during the operation fails.
     */
    public static boolean copyDirectory(File source, File target) throws IOException {
        // check first if source file exists or not
        if (!source.exists()) {
            return false;
        }
        if (source.isDirectory()) {
            if (!target.exists()) {
                target.mkdir();
            }

            File[] listFile = source.listFiles();
            for (File f : listFile) {
                File sourceFile = new File(source, f.getName());
                File outputFile = new File(target, f.getName());
                if (f.isDirectory()) {
                    copyDirectory(sourceFile, outputFile);
                } else {
                    copyFile(sourceFile, outputFile);
                }
            }
        }

        return true;
    }

    /**
     * Helper function used to delete a file. Directories are only deleted if empty.
     *
     * @param f - The file to delete.
     * @throws IOException - If anything during the operation fails.
     */
    public static void deleteFile(File f) throws IOException {
        deleteFile(f, false);
    }

    /**
     * Helper function used to delete a file.
     * If a directory and we want it, recursively delete all sub files and directories.
     *
     * @param f - The file to delete.
     * @param recursive - Whether to delete recursively files in folders. If not, only empty folder will be deleted.
     * @throws IOException - If anything during the operation fails.
     */
    public static void deleteFile(File f, boolean recursive) throws IOException {
        if (recursive && f.isDirectory()) {
            for (File c : f.listFiles()) {
                deleteFile(c, recursive);
            }
        }
        Files.deleteIfExists(f.toPath());
    }

    /**
     * Helper function to copy the file.
     * 
     * @param input - The input file.
     * @param output - The output file to which the input should be copied.
     * @throws IOException - Any exception thrown during the operation.
     */
    public static void copyFile(File input, File output) throws IOException {
        FileInputStream inputStream = new FileInputStream(input);
        // target file declaration
        FileOutputStream outputStream = new FileOutputStream(output);
        int lengthStream;
        byte[] buff = new byte[1024];
        while ((lengthStream = inputStream.read(buff)) > 0) {
            // writing to the target file contents of the source file
            outputStream.write(buff, 0, lengthStream);
        }
        outputStream.close();
        inputStream.close();
    }
}
