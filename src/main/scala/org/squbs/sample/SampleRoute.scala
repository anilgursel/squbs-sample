package org.squbs.sample

import akka.http.scaladsl.model.{StatusCodes, HttpRequest}
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}
import org.squbs.httpclient.ClientFlow
import org.squbs.unicomplex.RouteDefinition

import scala.util.{Failure, Success}

/**
  * The route definition.
  */
class SampleRoute extends RouteDefinition {

  implicit val system = context.system
  implicit val materializer = ActorMaterializer()
  val clientFlow = ClientFlow[Int]("maps")

  override def route: Route =
    get {
      path("hello") {
        complete("Hello World!")
      } ~ path("elevation" / Remaining) { coordinates =>
        onComplete (
          Source.single(HttpRequest(uri = "/maps/api/elevation/json?locations=" + coordinates) -> 42)
            .via(clientFlow)
            .runWith(Sink.head)
        ) {
          case Success((Success(response), _)) => complete(response)
          case Failure(ex) => complete(StatusCodes.InternalServerError)
        }
      }
    }
}
