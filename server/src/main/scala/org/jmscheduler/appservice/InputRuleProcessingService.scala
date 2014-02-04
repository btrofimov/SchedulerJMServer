package org.jmscheduler.appservice

import org.quartz.impl.StdSchedulerFactory
import org.quartz.JobBuilder.newJob
import org.quartz.TriggerBuilder.newTrigger
import org.quartz.SimpleScheduleBuilder._
import org.quartz.{JobKey, Job, JobExecutionContext, Scheduler}
import org.slf4j.LoggerFactory
import org.jmscheduler.appservice.ruleprocessors.{SRule, RuleProcessor}
import org.jmscheduler.infrastructure.sender.{Sender, JMSSender}
import org.jmscheduler.infrastructure.closablemanager.Closable
import org.jmscheduler.viewmodels.RuleId


object EventProcessingService{
  val CATEGORY="category";
  val ID="id";
}

abstract class EventProcessingService()(implicit sender:Sender) extends InputRuleHandler with Closable with SchedulerRefrence{
  import EventProcessingService._
  scheduler.start();

  override def setRule(ruleId : RuleId, rule: SRule) {

    val id = ruleId.getUniqueId

    val jobDetail =  newJob(classOf[EventJob]).withIdentity(id).build();
    val data = jobDetail.getJobDataMap();
    data.put(CATEGORY, ruleId.category);
    data.put(ID, ruleId.id);
    val trigger = rule.process(ruleId)
    scheduler.scheduleJob(jobDetail, trigger);

  }

  override def deleteRule(rule : RuleId) {
    scheduler.deleteJob(new JobKey(rule.getUniqueId))
  }

  override def stop() {
    scheduler.shutdown();
  }

  private class EventJob extends Job{
    val logger = LoggerFactory.getLogger(getClass);

    override def execute( context : JobExecutionContext){
      val data = context.getJobDetail().getJobDataMap()
      val ruleId = new RuleId(data.getString(CATEGORY),data.getString(ID))
      sender.fireEvent(ruleId)
      logger.debug("generating quartz event "+ ruleId.getUniqueId)
    }
  }

}



trait SchedulerRefrence{
  private[appservice] val scheduler = create

  def create : Scheduler
}

trait SchedulerFactory extends SchedulerRefrence{
  //self : CirconusServiceFactory =>
  override def create = new StdSchedulerFactory().getScheduler()
}

