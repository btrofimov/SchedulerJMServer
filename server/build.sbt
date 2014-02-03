import AssemblyKeys._ // put this at the top of the file

name := "jmscheduler"

version := "0.0.1"

organization := "org.dfoer"

scalaVersion := "2.10.3"



//scalaSource in Compile := baseDirectory.value / "src"

// set the Scala test source directory to be <base>/test
//scalaSource in Test := baseDirectory.value / "test"

// add a test dependency on ScalaCheck
//libraryDependencies += "org.scala-tools.testing" %% "scalacheck" % "1.8" % "test"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

libraryDependencies ++=  Seq(
    "com.fasterxml.jackson.core" % "jackson-databind" % "2.2.3"        % "compile,test",
    "org.quartz-scheduler" % "quartz"                 % "2.2.1"        % "compile,test",
    "org.quartz-scheduler" % "quartz-jobs"            % "2.2.1"        % "compile,test",
    "org.codehaus.jackson" % "jackson-mapper-asl"     % "1.9.12"       % "compile,test",
    "org.codehaus.jackson" % "jackson-core-asl"       % "1.9.12"       % "compile,test",
    "com.yuvimasory"       % "jerkson_2.10"           % "0.6.1"        % "compile,test",
    "com.github.scopt"     % "scopt_2.10"             % "3.2.0"        % "compile,test",
    "org.springframework"  % "spring-jms"            % "3.1.0.RELEASE" % "compile,test",
    "org.apache.activemq"  % "activemq-client"           % "5.9.0"        % "compile,test",
    "org.apache.activemq"  % "activemq-pool"           % "5.9.0"        % "compile,test",
    "org.mockito"          % "mockito-all"            % "1.9.5" % "test",
    "org.scalatest"        % "scalatest_2.10"         % "1.9.2" % "test"
  )





assemblySettings

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case x      => MergeStrategy.last
  }
}