/**
 * Copyright 2009 The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hbase.zookeeper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HBaseTestCase;
import org.apache.hadoop.hbase.HConstants;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.apache.zookeeper.server.quorum.QuorumPeer.QuorumServer;

/**
 * Test for HQuorumPeer.
 */
public class TestHQuorumPeer extends HBaseTestCase {
  private Path dataDir;

  /**
 */
public void testMakeZKProps() {
	Properties properties = HQuorumPeer.makeZKProps(conf);
	assertEquals(dataDir.toString(), properties.get("dataDir"));
	assertEquals(Integer.valueOf(21810), Integer.valueOf(properties.getProperty("clientPort")));
	assertEquals("localhost:2888:3888", properties.get("server.0"));
	assertEquals(null, properties.get("server.1"));
	String oldValue = conf.get(HConstants.ZOOKEEPER_QUORUM);
	conf.set(HConstants.ZOOKEEPER_QUORUM, "a.foo.bar,b.foo.bar,c.foo.bar");
	properties = HQuorumPeer.makeZKProps(conf);
	assertEquals(dataDir.toString(), properties.get("dataDir"));
	assertEquals(Integer.valueOf(21810), Integer.valueOf(properties.getProperty("clientPort")));
	assertEquals("a.foo.bar:2888:3888", properties.get("server.0"));
	assertEquals("b.foo.bar:2888:3888", properties.get("server.1"));
	assertEquals("c.foo.bar:2888:3888", properties.get("server.2"));
	assertEquals(null, properties.get("server.3"));
	conf.set(HConstants.ZOOKEEPER_QUORUM, oldValue);
}
}
