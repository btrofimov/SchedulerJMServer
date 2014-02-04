jmscheduler
=================

Standalone Scheduler Server based on Terracotta Quartz, allows seting ruels and firing events through JMS. 
Very suitable component for scalable solutions.

## Current Status
 * under cunsrtuction
 * integration tests and unit tests are in progress
 * Version 1.0 is coming up


## version 1.0
### Features:
 * asynchronous bidirectional interface through JMS (setting event rules, triggering event notifications)
 * support ActiveMQ and queue type
 * Set one-time as well as periodic (crontab like) rules
 * Standalone application executor
 * configurable JMS settings
 * Java client library to interact to jmscheduler

### Limitations
 * No persistent storage, all rules are stored in RAM. Application restart will lead to resetting all rules.

This version is our primary goal.


2.0 version
This version is in future plans.
 * Persistent rule storage.
 * Event triggering will support topic as well as queue type.
 * Respecting daylight time saving.
 * Set crontab like rules
 * Add support of RabbitMQ
 * JEE/EAR component executor

