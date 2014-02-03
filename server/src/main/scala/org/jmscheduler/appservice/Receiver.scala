package org.jmscheduler.appservice

import java.util.Date
import org.codehaus.jackson.map.ObjectMapper
import org.jmscheduler.domain._
import org.jmscheduler.domain.PointRule
import org.jmscheduler.utils.VoidAspect
import org.jmscheduler.utils.VoidAspect._
import javax.jms.{Session, TextMessage}
import org.springframework.jms.listener.SessionAwareMessageListener

class InvalidRuleException( rule : String) extends RuntimeException("Incorrect rule %s" format rule)
class InvalidCommandException( cmd : String) extends RuntimeException("Incorrect command %s" format cmd)

class Receiver (implicit handler : InputRuleHandler) extends VoidAspect with SessionAwareMessageListener[TextMessage] {
  private val mapper = new ObjectMapper()


  implicit private def ruleFactory(rule : Rule) : SRule = {
    rule.getRuleType match {
      case "point" => PointRule(new Date(rule.rule.toLong))
      case "periodic" => {val tmp = rule.rule.split(',').map(_.toInt); PeriodicRule(tmp(0), new Date(tmp(0))) }
      case unknown: String => throw new InvalidRuleException(unknown)
    }
  }



  override def onMessage(message: TextMessage, session: Session) = voidAspect {

    val command = mapper.readValue(message.getText, classOf[InputCommand])
    command.command match {
      case "add" =>     handler.setRule(command.getRuleId, command.rule )
      case "remove" =>  handler.deleteRule(command.getRuleId)
      case unknown: String => throw new InvalidCommandException(unknown)
    }
  }
}