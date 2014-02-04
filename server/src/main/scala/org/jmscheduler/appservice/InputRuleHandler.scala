package org.jmscheduler.appservice

import org.jmscheduler.domain.{SRule, RuleId}

trait InputRuleHandler{
  def setRule(ruleId : RuleId, rule: SRule)
  def deleteRule(rule : RuleId)
}
