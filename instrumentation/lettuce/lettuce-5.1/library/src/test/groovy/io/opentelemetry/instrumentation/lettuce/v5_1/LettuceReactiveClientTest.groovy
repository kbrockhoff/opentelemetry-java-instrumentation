/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.instrumentation.lettuce.v5_1

import io.lettuce.core.RedisClient
import io.lettuce.core.resource.ClientResources
import io.opentelemetry.instrumentation.reactor.TracingOperator
import io.opentelemetry.instrumentation.test.LibraryTestTrait

class LettuceReactiveClientTest extends AbstractLettuceReactiveClientTest implements LibraryTestTrait {
  @Override
  RedisClient createClient(String uri) {
    return RedisClient.create(
      ClientResources.builder()
        .tracing(LettuceTracing.create(getOpenTelemetry()).newTracing())
        .build(),
      uri)
  }

  def setupSpec() {
    TracingOperator.registerOnEachOperator()
  }

  def cleanupSpec() {
    TracingOperator.resetOnEachOperator()
  }
}
