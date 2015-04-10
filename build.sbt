name := """moneybackward"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.seleniumhq.selenium" % "selenium-chrome-driver" % "2.45.0",
  "org.seleniumhq.selenium" % "selenium-firefox-driver" % "2.45.0",
  "org.seleniumhq.selenium" % "selenium-java" % "2.45.0",
  "org.fluentlenium" % "fluentlenium-core" % "0.10.3",
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "com.typesafe.play" %% "play-slick" % "0.8.1",
  "org.jsoup" % "jsoup" % "1.8.1",
  "joda-time" % "joda-time" % "2.4",
  "org.joda" % "joda-convert" % "1.6",
  "com.github.tototoshi" %% "slick-joda-mapper" % "1.2.0",
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars.bower" % "bootstrap" % "3.3.4",
  "org.webjars.bower" % "jquery" % "1.11.0"
)

LessKeys.compress in Assets := true

pipelineStages := Seq(digest, gzip)

includeFilter in (Assets, LessKeys.less) := "*.less"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
