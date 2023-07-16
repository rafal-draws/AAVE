package utils


class FileNameCreator() {
  def returnDevFilepath(date: String): String = {
    val os: String = System.getProperty("os.name")
    os match {
      case x if x.startsWith("Windows") => System.getProperty("user.dir") + "\\data\\" + date + ".json"
      case x if x.startsWith("Linux") => System.getProperty("user.dir") + "/data/" + date + ".json"
      case _ => System.getProperty("user.dir") + date + ".json"
    }
  }

  def returnFilepath(date: String): String = {
    val path = sys.env.getOrElse("DATA_STORAGE", "/data/")
    val filepath = s"$path/$date.json"

    filepath
  }

  def getLandingFolderFromArgs(path: String, date: String): String = s"$path$date.json"


}
