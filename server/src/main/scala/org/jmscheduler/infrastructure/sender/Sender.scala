package org.jmscheduler.infrastructure.sender

import org.jmscheduler.domain.RuleId

trait Sender {
  def fireEvent(ruleId: RuleId)
}
