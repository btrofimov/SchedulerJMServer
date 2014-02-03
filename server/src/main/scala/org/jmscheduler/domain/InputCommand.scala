package org.jmscheduler.domain

class RuleId{
  @scala.reflect.BeanProperty
  var category:String = _
  @scala.reflect.BeanProperty
  var id:String = _

  def getUniqueId = category + id

  def this(category1:String, id1:String) = {this;category=category1;id=id1;}
}
class InputCommand(){
  @scala.reflect.BeanProperty
  var ruleId: RuleId= _
  @scala.reflect.BeanProperty
  var command: String= _ // add or remove
  @scala.reflect.BeanProperty
  var rule : Rule = _
}

class Rule(){
  @scala.reflect.BeanProperty
  var ruleType: String = _ // point, periodic
  @scala.reflect.BeanProperty
  var rule: String = _ // data serialized into string
}