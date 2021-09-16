package ru.sber.io

import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile(fileName: String) {
        ZipOutputStream(FileOutputStream("io/logfile.zip")).use { zos ->
            FileInputStream(fileName).use { fis ->
                val entry = ZipEntry("logfile.log")
                zos.putNextEntry(entry)

                val buffer = ByteArray(fis.available())
                fis.read(buffer)
                zos.write(buffer)
            }
        }
    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile(zipName: String) {
        ZipInputStream(FileInputStream(zipName)).use { zis ->
            FileOutputStream("io/unzippedLogfile.log").use { fos ->
                while (zis.nextEntry != null) {
                    var c = zis.read()
                    while (c != -1) {
                        fos.write(c)
                        c = zis.read()
                    }
                }
            }
        }
    }
}