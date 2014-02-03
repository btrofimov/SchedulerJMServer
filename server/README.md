SchedulerJMServer
=================

Standalone Scheduler Server based on Terracotta Quartz, allows triggering event notifications through JMS. Very suitable component for scalable solutions.


Features:

 * asynchronous bidirectional interface through JMS (setting event rules, triggering event notifications)
 * Event triggering will support topic as well as queue type.
 * Set one-time as well as periodic (crontab like) rules
 * Standalone application executor
 * JEE/EAR component executor
 * configurable JMS settings



1.0 version
No persistent storage, all rules are stored in RAM. Application restart will lead to resetting all rules.

This version is our primary goal.


2.0 version
This version is in future plans.
 * Persistent rule storage.
 * Respecting daylight time saving.

