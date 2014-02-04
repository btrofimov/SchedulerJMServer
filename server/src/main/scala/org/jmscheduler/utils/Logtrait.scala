package org.jmscheduler.utils

import org.slf4j.LoggerFactory

trait LogTrait {
  self =>
  protected val logger = LoggerFactory.getLogger(self.getClass);
}