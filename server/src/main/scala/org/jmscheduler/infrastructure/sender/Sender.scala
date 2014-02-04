package org.jmscheduler.infrastructure.sender

import org.jmscheduler.viewmodels.RuleId

trait Sender {
  def fireEvent(ruleId: RuleId)
}
