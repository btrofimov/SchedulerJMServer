package org.jmscheduler.appservice.ruleprocessors

import java.util.Date
import org.jmscheduler.utils.{ExceptionMessage, ConvertExceptionAspect}

class PointRuleProcessor() extends RuleProcessor with ConvertExceptionAspect{

  implicit def exceptionMessage = ExceptionMessage("Invalid point rule")

  override def createRuleInstance(rule : String) = aspect{
    new PointRule(new Date(rule.toLong))
  }
}