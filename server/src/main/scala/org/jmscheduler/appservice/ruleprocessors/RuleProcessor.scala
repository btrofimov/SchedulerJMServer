package org.jmscheduler.appservice.ruleprocessors

import org.quartz.Trigger
import org.jmscheduler.domain.{SRule, Rule, RuleId}


trait RuleProcessor{
  def process(ruleId : RuleId, rule: SRule) : Trigger
}