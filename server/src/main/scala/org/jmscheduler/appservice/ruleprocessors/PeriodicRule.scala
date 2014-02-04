package org.jmscheduler.appservice.ruleprocessors

import java.util.Date
import org.jmscheduler.viewmodels.RuleId
import org.quartz.TriggerBuilder._

import org.quartz.SimpleScheduleBuilder._

/**
 *
 * @param period
 * @param start
 */
class PeriodicRule(period: Int, start : Date) extends SRule{
  def process( ruleId : RuleId)  = {
    val id = ruleId.toString
    val trigger = newTrigger()
      .withIdentity(id)
      .startNow()
      .withSchedule(simpleSchedule().withIntervalInSeconds(period))
      .forJob(id)
      .build();
    trigger
  }
}
