package org.jmscheduler.domain

import java.util.Date

abstract class SRule {
  def getClassId : Symbol
}

case class PointRule(dt: Date) extends SRule{

  override def getClassId = 'Point
}

case class PeriodicRule(period: Int, start : Date) extends SRule{
  override def getClassId = 'Periodic
}


