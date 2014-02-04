package org.jmscheduler.appservice.ruleprocessors

import org.jmscheduler.viewmodels.RuleId
import org.quartz.Trigger


abstract class SRule {
  def process( ruleId : RuleId) : Trigger
}
