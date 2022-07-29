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
 * @throws Exception  
 */public void testConfigInjection() throws Exception{String s="dataDir=${hbase.tmp.dir}/zookeeper\n" + "clientPort=2181\n" + "server.0=${hbase.master.hostname}:2888:3888\n";System.setProperty("hbase.master.hostname","localhost");InputStream is=new ByteArrayInputStream(s.getBytes());Properties properties=HQuorumPeer.parseZooCfg(conf,is);assertEquals(dataDir.toString(),properties.get("dataDir"));assertEquals(Integer.valueOf(2181),Integer.valueOf(properties.getProperty("clientPort")));assertEquals("localhost:2888:3888",properties.get("server.0"));QuorumPeerConfig config=new QuorumPeerConfig();config.parseProperties(properties);assertEquals(dataDir.toString(),config.getDataDir());assertEquals(2181,config.getClientPortAddress().getPort());Map<Long, QuorumServer> servers=config.getServers();assertEquals(1,servers.size());assertTrue(servers.containsKey(Long.valueOf(0)));QuorumServer server=servers.get(Long.valueOf(0));assertEquals("localhost",server.addr.getHostName());System.setProperty("hbase.master.hostname","foo.bar");is=new ByteArrayInputStream(s.getBytes());properties=HQuorumPeer.parseZooCfg(conf,is);assertEquals("foo.bar:2888:3888",properties.get("server.0"));config.parseProperties(properties);servers=config.getServers();server=servers.get(Long.valueOf(0));assertEquals("foo.bar",server.addr.getHostName());}
}
