package org.jmscheduler.infrastructure.closablemanager


class ClosableManager(closables : List[Closable]) {
  def stop {
    closables.foreach( f=> f.stop)
  }
}
