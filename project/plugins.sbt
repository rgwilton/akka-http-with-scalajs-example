// fast development turnaround when using sbt ~re-start
addSbtPlugin("io.spray" % "sbt-revolver" % "0.8.0")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.3")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.1.4")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.17")

addSbtPlugin("com.vmunier" % "sbt-web-scalajs" % "1.0.5")

addSbtPlugin("com.vmunier" % "sbt-web-scalajs" % "1.0.5")

addSbtPlugin("com.typesafe.sbt" % "sbt-twirl" % "1.3.0")

// Rename asserts with unique name to allow for aggressive caching.
addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.2")

// RequireJS optimizations and uglify (JS minification)
addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.9")

// gzip assets to make them smaller.
addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.1")