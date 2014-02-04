package org.jmscheduler.utils

object VoidAspect{

  implicit val unit :Unit= Unit
}

trait VoidAspect extends LogTrait{

  def voidAspect[T](code : => T)(implicit defaultValue: T) : T = {
    try{
      code
    }
    catch{
      case e : Exception  => {
        logger.error("error on class %s with error %s" format (getClass, e.toString ))
        defaultValue
      }
    }
  }
}
