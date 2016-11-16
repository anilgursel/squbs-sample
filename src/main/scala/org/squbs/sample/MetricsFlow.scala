package org.squbs.sample

import akka.actor.ActorSystem
import org.squbs.metrics.MetricsFlow
import org.squbs.pipeline.streaming._

// This could actually be in a library.
class MetricsFlow extends PipelineFlowFactory {

  override def create(context: Context)(implicit system: ActorSystem): PipelineFlow = {
    MetricsFlow(context.name)
  }
}
