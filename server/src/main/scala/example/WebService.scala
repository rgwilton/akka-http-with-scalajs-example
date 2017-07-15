package example

import akka.http.scaladsl.model.headers.HttpEncodings.gzip
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
      //  }
      //}
    } ~
      pathPrefix("assets" / Remaining ) { file =>
        // Serve up the gzipped version of the asset if the client accepts that.
        responseEncodingAccepted(gzip) {
          getFromResource("public/" + file + ".gz")
        } ~ {
          getFromResource("public/" + file)
        }
    }
  }
}
