/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.instrumentation.grpc.v1_6;

import io.grpc.Status;
import io.opentelemetry.instrumentation.api.instrumenter.net.InetSocketAddressNetAttributesExtractor;
import io.opentelemetry.semconv.trace.attributes.SemanticAttributes;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import org.checkerframework.checker.nullness.qual.Nullable;

final class GrpcNetAttributesExtractor
    extends InetSocketAddressNetAttributesExtractor<GrpcRequest, Status> {
  @Override
  @Nullable
  public InetSocketAddress getAddress(GrpcRequest grpcRequest, @Nullable Status status) {
    SocketAddress address = grpcRequest.getRemoteAddress();
    if (address instanceof InetSocketAddress) {
      return (InetSocketAddress) address;
    }
    return null;
  }

  @Override
  @Nullable
  public String transport(GrpcRequest grpcRequest) {
    return SemanticAttributes.NetTransportValues.IP_TCP;
  }
}
