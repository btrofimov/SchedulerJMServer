package org.jmscheduler.viewmodels


class RuleId{
  @scala.reflect.BeanProperty
  var category:String = _
  @scala.reflect.BeanProperty
  var id:String = _

  def getUniqueId = category + id

  def this(category1:String, id1:String) = {this;category=category1;id=id1;}
}
