package org.squbs.sample

import org.squbs.endpoint.{Endpoint, EndpointResolver, EndpointResolverRegistry}
import org.squbs.env.Environment
import org.squbs.lifecycle.ExtensionLifecycle

class AppExtension extends ExtensionLifecycle {

  override def preCubesInit(): Unit = EndpointResolverRegistry(boot.actorSystem).register(MyEndpointResolver)
}

object MyEndpointResolver extends EndpointResolver {

  override def name: String = "MyEndpointResolver"

  override def resolve(svcName: String, env: Environment): Option[Endpoint] = svcName match {
    case "maps" => Some(Endpoint("https://maps.googleapis.com:443"))
    case _ => None
  }
}
