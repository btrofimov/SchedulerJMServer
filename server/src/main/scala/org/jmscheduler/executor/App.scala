package org.jmscheduler.application

import org.jmscheduler.executor.{AppConfig, AppContext}
import org.jmscheduler.utils.LogTrait

class ConsoleAppClass(appConfig : AppConfig)  extends AppContext(appConfig) with LogTrait  {
    try {
      logger.info("server started.");
      readChar()
      closableManager.stop
      logger.info("done.");
    }catch{
      case e:Exception => {
        logger.error(e.toString)
        throw e;
      }
    }
}

object ConsoleApp  extends App with LogTrait{
  val parser = prepareParser(args)
  parser.parse(args, AppConfig()) map { config => new ConsoleAppClass(config);
  } getOrElse {
    logger.error(parser.usage)
    // arguments are bad, error message will have been displayed
    throw new IllegalArgumentException()
  }

  private def prepareParser(args:Array[String]) = {

    val parser =  new scopt.OptionParser[AppConfig]("jmscheduler") {
      head("","1.1")
      help("help") text("test help")
      opt[String]('u', "url") required() action { (x, c) =>
        c.copy(url = x) } text("JMS url, like failover:tcp://localhost:61616")

      opt[String]('i', "inQueueName") optional() action { (x, c) =>
        c.copy(inQueueName = x) } text("inQueueName, like jmscheduler.addRule")

      opt[String]('o', "outQueueName") optional() action { (x, c) =>
        c.copy(outQueueName = x) } text("outQueueName, like jmscheduler.fireEvent")

      //opt[Unit]('f',"failover") optional() action { (_, c) =>
      //  c.copy(failover = true) } text("failover")
    }
    parser
  }

}