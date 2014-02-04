package org.jmscheduler.viewmodels


class InputCommand(){
  @scala.reflect.BeanProperty
  var ruleId: RuleId= _
  @scala.reflect.BeanProperty
  var command: String= _ // add or remove
  @scala.reflect.BeanProperty
  var rule : Rule = _
}
