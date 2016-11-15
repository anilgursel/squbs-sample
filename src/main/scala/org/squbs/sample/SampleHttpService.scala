package org.squbs.sample

import akka.http.scaladsl.server.Route
import org.squbs.unicomplex.RouteDefinition

import scala.language.postfixOps

/**
  * The route definition.
  */
class SampleHttpSvc extends RouteDefinition {

  override def route: Route =
    get {
      path("hello") {
        complete("Hello World!")
      }
    }
}
