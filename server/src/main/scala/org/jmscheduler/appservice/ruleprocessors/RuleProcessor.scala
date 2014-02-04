package org.jmscheduler.appservice.ruleprocessors


trait RuleProcessor{
  def createRuleInstance(rule : String) : SRule
}