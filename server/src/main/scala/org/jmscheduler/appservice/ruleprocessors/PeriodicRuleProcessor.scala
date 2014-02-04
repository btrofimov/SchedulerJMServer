package org.jmscheduler.appservice.ruleprocessors

import org.jmscheduler.domain.{Rule, PeriodicRule, RuleId, SRule}
import org.quartz.{SimpleTrigger, Trigger}
import org.quartz.TriggerBuilder.newTrigger
import org.quartz.SimpleScheduleBuilder._

class PeriodicRuleProcessor() extends RuleProcessor{

  override def process( ruleId: RuleId, rule:SRule) = {
    val arule = rule.asInstanceOf[PeriodicRule]
    val id = ruleId.toString
    val trigger = newTrigger()
      .withIdentity(id)
      .startNow()
      .withSchedule(simpleSchedule().withIntervalInSeconds(arule.period))
      .forJob(id)
      .build();
    trigger
  }
}