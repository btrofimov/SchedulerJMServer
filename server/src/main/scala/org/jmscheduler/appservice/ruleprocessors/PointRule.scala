package org.jmscheduler.appservice.ruleprocessors

import java.util.Date
import org.jmscheduler.viewmodels.RuleId
import org.quartz.TriggerBuilder._


class PointRule(dt: Date) extends SRule{

  def process( ruleId : RuleId)  = {
    val id = ruleId.toString
    val trigger = newTrigger()
      .withIdentity(id)
      .startAt(dt)
      .forJob(id)
      .build();
    trigger
  }
}


