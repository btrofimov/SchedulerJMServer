package org.jmscheduler.appservice.ruleprocessors

import org.jmscheduler.domain._
import org.quartz.TriggerBuilder._

class PointRuleProcessor() extends RuleProcessor{

  override def process(ruleId: RuleId, rule: SRule) = {
    val arule = rule.asInstanceOf[PointRule]
    val id = ruleId.toString
    val trigger = newTrigger()
      .withIdentity(id)
      .startAt(arule.dt)
      .forJob(id)
      .build();
    trigger
  }
}