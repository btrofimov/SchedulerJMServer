package org.jmscheduler.utils

case class ExceptionMessage(message : String)

trait ConvertExceptionAspect {
  def aspect[T](code : => T)(implicit  exceptionMessage : ExceptionMessage) : T = {
    try{
      code
    }
    catch{
      case e : Exception  => {
        throw new RuntimeException(exceptionMessage.message, e)
      }
    }
  }
}