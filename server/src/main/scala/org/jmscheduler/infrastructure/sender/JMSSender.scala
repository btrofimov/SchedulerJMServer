package org.jmscheduler.infrastructure.sender

import org.springframework.jms.core.{MessageCreator, JmsTemplate}
import javax.jms.Session
import org.codehaus.jackson.map.ObjectMapper
import org.jmscheduler.utils.LogTrait
import org.jmscheduler.viewmodels.RuleId


class JMSSender(implicit jmsTemplate : JmsTemplate) extends Sender with LogTrait{

  private val mapper = new ObjectMapper()

  override def fireEvent(ruleId: RuleId) {
    jmsTemplate.send(new MessageCreator() {
      override def createMessage(session : Session) = {
        val message = mapper.writeValueAsString(ruleId)
        logger.debug("sending message %s" format message)
        session.createTextMessage(message);
      }
    });
  }
}