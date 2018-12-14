name := "screenrecorder"

version := "0.1"

scalaVersion := "2.12.8"

val jcodecVersion = "0.2.3"

libraryDependencies.++=(Seq(
  "org.jcodec" % "jcodec" % jcodecVersion,
  "org.jcodec" % "jcodec-javase" % jcodecVersion
))