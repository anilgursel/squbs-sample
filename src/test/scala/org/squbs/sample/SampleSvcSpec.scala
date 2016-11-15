package org.squbs.sample

import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import org.scalatest.{FlatSpecLike, Matchers}
import org.squbs.testkit.TestRoute

import scala.concurrent.duration._
import scala.language.postfixOps

class SampleSvcSpec extends FlatSpecLike with Matchers with ScalatestRouteTest {

  implicit val timeout = RouteTestTimeout(5 seconds)

  val route = TestRoute[SampleHttpSvc]

  it should "handle path correctly" in {
    Get("/hello") ~> route ~> check {
      responseAs[String] should be ("Hello World!")
    }
  }
}
