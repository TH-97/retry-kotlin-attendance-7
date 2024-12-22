package attendance

import attendance.model.Attendances
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class FileManager {
    fun importFile(fileList: MutableList<Attendances>) {
        val file = File("src/main/resources/attendances.csv")
        val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
        reader.readLines().drop(1).forEach {
            val fileData = it.split(",")
            fileList.add(Attendances(fileData[0], fileData[1]))
        }
    }
}