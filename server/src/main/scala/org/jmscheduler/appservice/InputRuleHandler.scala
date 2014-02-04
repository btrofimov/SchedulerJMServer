package org.jmscheduler.appservice

import org.jmscheduler.viewmodels.RuleId
import org.jmscheduler.appservice.ruleprocessors.SRule

trait InputRuleHandler{
  def setRule(ruleId : RuleId, rule: SRule)
  def deleteRule(rule : RuleId)
}
