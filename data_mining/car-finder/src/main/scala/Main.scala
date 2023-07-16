import engines.OTOMOTOScrapingEngine
import utils.{FileNameCreator, OutputGenerator, ParameterFileReader}

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Main {

  def main(args: Array[String]) : Unit = {


    val date: String = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH").format(LocalDateTime.now)
    val otomotoScrapingEngine: OTOMOTOScrapingEngine = new OTOMOTOScrapingEngine()
    val parameterFileReader: ParameterFileReader = new ParameterFileReader()
    val outputGenerator: OutputGenerator = new OutputGenerator()
    val filenameCreator: FileNameCreator = new FileNameCreator()

    
    val filename = if (args.length >= 1) {
      println(s"path provided: ${args(0)}")
      filenameCreator.getLandingFolderFromArgs(args(0), date)
    } else filenameCreator.returnFilepath(date)

    val withPhotos: Boolean = if (args.length >= 2) args(1).toBoolean else false

    val otomotoSearchParametersList: List[(String, String, BigInt, BigInt)] = parameterFileReader.readForOtomoto("parameters.json", System.getProperty("os.name"))

    val startTime = System.nanoTime()

    outputGenerator.createOutputFile(filename)
    otomotoSearchParametersList.foreach(x => otomotoScrapingEngine.initiateOTOMOTOScraping(x, filename, withPhotos))

    val endTime = System.nanoTime()
    val timeSpent = (endTime - startTime).toDouble / 1000000000.0
    println(s"Time taken: $timeSpent")

    outputGenerator.removeComma(filename)
    outputGenerator.endOutputFile(filename)
  }

}


