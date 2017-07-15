val scalaV = "2.12.2"

lazy val server = (project in file("server")).settings(
  scalaVersion := scalaV,
  scalaJSProjects := Seq(client),
  pipelineStages in Assets := Seq(scalaJSPipeline/*, rjs*/, digest, gzip),
  // triggers scalaJSPipeline when using compile or continuous compilation
  compile in Compile := ((compile in Compile) dependsOn scalaJSPipeline).value,
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-http" % "10.0.7",
    "com.vmunier" %% "scalajs-scripts" % "1.1.0"

    // Client side dependencies.
    //"org.webjars" % "foundation" % "6.3.1"
    //"org.webjars" % "font-awesome" % "4.7.0"
  ),
  WebKeys.packagePrefix in Assets := "public/",
  managedClasspath in Runtime += (packageBin in Assets).value,
  // Compile the project before generating Eclipse files, so that generated .scala or .class files for Twirl templates are present
  EclipseKeys.preTasks := Seq(compile in Compile),

  // Write a javascript map from short name to full unique name.
  DigestKeys.indexPath := Some("javascripts/versioned.js")
).enablePlugins(SbtWeb, JavaAppPackaging).
  dependsOn(sharedJvm, client)

lazy val client = (project in file("client")).settings(
  scalaVersion := scalaV,
  scalaJSUseMainModuleInitializer := true,
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.1",
    "com.github.japgolly.scalajs-react" %%% "core" % "1.1.0",
    "com.github.japgolly.scalajs-react" %%% "extra" % "1.1.0",

    // Client side dependencies.
    "org.webjars" % "foundation" % "6.3.1",
    "org.webjars" % "font-awesome" % "4.7.0",
    "org.webjars.bower" % "react" % "15.6.1"
  )
  // Mucks up the path.
  //WebKeys.packagePrefix in Assets := "public/",
  //managedClasspath in Runtime += (packageBin in Assets).value
).enablePlugins(SbtWeb, ScalaJSPlugin, ScalaJSWeb).
  dependsOn(sharedJs)

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared")).
  settings(
  scalaVersion := scalaV).
  enablePlugins(ScalaJSWeb)
//  jsConfigure(_ enablePlugins ScalaJSWeb)

lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js

// Test
//lazy val printClasspath = task {
//  this.runClasspath.getPaths.foreach(println);
//  None
//}

// loads the server project at sbt startup
onLoad in Global := (Command.process("project server", _: State)) compose (onLoad in Global).value
