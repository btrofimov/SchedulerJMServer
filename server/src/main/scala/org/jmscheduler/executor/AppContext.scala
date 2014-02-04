package  org.jmscheduler.executor

import org.jmscheduler.appservice.{Receiver, SchedulerFactory, EventProcessingService, InputRuleHandler}
import org.jmscheduler.infrastructure.sender.{Sender, JMSSender}
import org.jmscheduler.appservice.ruleprocessors.{PeriodicRuleProcessor, PointRuleProcessor, RuleProcessor}
import org.jmscheduler.infrastructure.closablemanager.{Closable, ClosableManager}
import org.apache.activemq.pool.PooledConnectionFactory

case class AppConfig(url: String = null, inQueueName: String = null, outQueueName: String = null, failover : Boolean = false)

abstract class AppContext (config : AppConfig){

  val processors = Map('point -> new PointRuleProcessor, 'periodic -> new PeriodicRuleProcessor)


  implicit val jmsConnectionFactory = new org.apache.activemq.ActiveMQConnectionFactory(){setBrokerURL(config.url)}
  implicit val pooledJmsConnectionFactory = new org.apache.activemq.pool.PooledConnectionFactory(){setConnectionFactory(jmsConnectionFactory) }

  implicit val outQueue = new org.apache.activemq.command.ActiveMQQueue(config.outQueueName)

  implicit val jmsTemplate = new org.springframework.jms.core.JmsTemplate(){
    setConnectionFactory(pooledJmsConnectionFactory)
    setDefaultDestination(outQueue)
  }

  implicit val inQueue = new org.apache.activemq.command.ActiveMQQueue(config.inQueueName)


  implicit val sender = new JMSSender()


  implicit val inputProcessor = new EventProcessingService() with SchedulerFactory



  implicit val receiver = new Receiver(processors)

  implicit val jmsConsumer = new org.springframework.jms.listener.DefaultMessageListenerContainer(){
    setConnectionFactory(pooledJmsConnectionFactory)
    setDestination(inQueue)
    setMessageListener(receiver)
  }



  implicit def fromPooledConnectionFactoryToClosable(pooledJmsConnectionFactory: PooledConnectionFactory) = new Closable{
    override def stop(){
      pooledJmsConnectionFactory.stop
    }
  }

  implicit val closableManager = new ClosableManager( List(inputProcessor, pooledJmsConnectionFactory ))

}
