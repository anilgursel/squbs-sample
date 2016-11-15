package org.squbs.sample

import akka.http.scaladsl.server.Route
import org.squbs.unicomplex.RouteDefinition

/**
  * The route definition.
  */
class SampleRoute extends RouteDefinition {

  override def route: Route =
    get {
      path("hello") {
        complete("Hello World!")
      }
    }
}
