package org.jmscheduler.appservice.ruleprocessors

import java.util.Date
import org.jmscheduler.utils.{ExceptionMessage, ConvertExceptionAspect}

class PeriodicRuleProcessor() extends RuleProcessor with ConvertExceptionAspect{

  implicit def exceptionMessage = ExceptionMessage("Invalid periodic rule")

  override def createRuleInstance(rule : String) = aspect{
    val tmp = rule.split(',').map(_.toInt)
    new PeriodicRule(tmp(0), new Date(tmp(0)))
  }
}