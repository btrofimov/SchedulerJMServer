package com.collective.pythia

import org.specs2.mutable._
import org.mockito.Matchers._

class ConfigParserSpec extends Specification with Mockito{

  "Config String Parser when configuration path is valid" should {
    "parse correctly configuration file with non-empty values" in {
      val ptree = ConfigParser.parse_ikebana_file(valid_config_path);
      tree.isInstanceOf[Test] must beEqualTo(true);

      tree._1.size must beEqualTo(2);

      val datapair = ptree._1 get("5");
      datapair must beSome;

      ptree._2.size must beEqualTo(2);

      val behavior = ptree._2 get("44");
      behavior must beSome;
      behavior.list.size must beEqualTo(2);

      tree._2.size must beEqualTo(1);
      tree._2.get("test").get must beEqualTo("3245");
    }
  }
}
