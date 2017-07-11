package example

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives
import shared.SharedMessages

class WebService() extends Directives {

  val route:Route = {
    pathSingleSlash {
      //get {
      //  complete {
          //getFromBrowseableDirectories("")
          getFromResource("public/lib/client/index.html")
          //"Need to serve up a file instead."
          //.html.index.render(SharedMessages.itWorks)
      //  }
      //}
    } ~
      pathPrefix("assets" / Remaining) { file =>
        // optionally compresses the response with Gzip or Deflate
        // if the client accepts compressed responses
        encodeResponse {
          getFromResource("public/" + file)
        }
      }
  }
}
