#!/bin/sh

baseDir="$( cd "$( dirname "$0" )"/.. && pwd )"

countScalaFiles() {
  archive="$baseDir/server/target/universal/server-0.1-SNAPSHOT.zip"
  unzip -o $archive
  nbScalaFiles=$(unzip -l "server-0.1-SNAPSHOT/lib/*server*.jar" | grep ".*\.scala$" | wc -l)
  return "$nbScalaFiles"
}

cd $baseDir

# produce archive with no source maps
sbt universal:packageBin
countScalaFiles
nbScalaFilesNoSourceMaps=$?

# produce archive with source maps
sbt universal:packageBin "set emitSourceMaps in (client, fullOptJS) := true" "set emitSourceMaps in (sharedJs, fullOptJS) := true" universal:packageBin
countScalaFiles
nbScalaFilesWithSourceMaps=$?

[ "$nbScalaFilesNoSourceMaps" -eq "0" ] && [ "$nbScalaFilesWithSourceMaps" -gt "0" ]