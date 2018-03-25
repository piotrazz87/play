name := "play2"
 
version := "1.0" 
      
lazy val `play2` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"
libraryDependencies += "com.h2database" % "h2" % "1.4.196"
libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.2",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.2"
)
unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

      