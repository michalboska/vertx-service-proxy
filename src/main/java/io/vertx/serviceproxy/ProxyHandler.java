/*
 * Copyright 2014 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

package io.vertx.serviceproxy;

import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;

/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public abstract class ProxyHandler implements Handler<Message<JsonObject>> {

  protected boolean closed;
  protected MessageConsumer<JsonObject> consumer;

  public void setConsumer(MessageConsumer<JsonObject> consumer) {
    this.consumer = consumer;
  }

  public void close() {
    consumer.unregister();
    closed = true;
  }

  /**
   * Register the proxy handle on the event bus.
   *
   * @param address the proxy address
   * @return the registered message consumer
   */
  public abstract MessageConsumer<JsonObject> registerHandler(String address);


  /**
   * Register the local proxy handle on the event bus.
   * The registration will not be propagated to other nodes in the cluster.
   *
   * @param address the proxy address
   * @return the registered message consumer
   */
  public abstract MessageConsumer<JsonObject> registerLocalHandler(String address);

}
