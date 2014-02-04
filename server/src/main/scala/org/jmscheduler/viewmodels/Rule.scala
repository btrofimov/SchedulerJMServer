package org.jmscheduler.viewmodels

class Rule {
  @scala.reflect.BeanProperty
  var ruleType: String = _ // point, periodic
  @scala.reflect.BeanProperty
  var rule: String = _ // data serialized into string
}