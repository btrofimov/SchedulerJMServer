package org.jmscheduler.appservice


import org.codehaus.jackson.map.ObjectMapper

import org.jmscheduler.utils.VoidAspect
import org.jmscheduler.utils.VoidAspect._
import javax.jms.{Session, TextMessage}
import org.springframework.jms.listener.SessionAwareMessageListener
import org.jmscheduler.viewmodels.InputCommand
import org.jmscheduler.appservice.ruleprocessors.{RuleProcessor, SRule}
import org.jmscheduler.viewmodels.Rule

class InvalidRuleException( rule : String) extends RuntimeException("Unknown rule %s" format rule)
class InvalidCommandException( cmd : String) extends RuntimeException("Incorrect command %s" format cmd)

object Receiver{

  // commands
  val ADD = "add"
  val REMOVE = "remove"
}

class Receiver (ruleProcessors : Symbol => RuleProcessor)(implicit handler : InputRuleHandler) extends VoidAspect with SessionAwareMessageListener[TextMessage] {
  import Receiver._
  private val mapper = new ObjectMapper()

  implicit private def ruleFactory(rule : Rule) : SRule = {
    val ret = ruleProcessors(Symbol(rule.getRuleType))
    if(ret==null)
      throw new InvalidRuleException(rule.getRuleType)
    ret.createRuleInstance(rule.rule)
  }

  override def onMessage(message: TextMessage, session: Session) = voidAspect {

    val command = mapper.readValue(message.getText, classOf[InputCommand])
    command.command match {
      case ADD =>     handler.setRule(command.getRuleId, command.rule )
      case REMOVE =>  handler.deleteRule(command.getRuleId)
      case unknown: String => throw new InvalidCommandException(unknown)
    }
  }
}