/** 
 * @throws Exception 
 */
public void testConfigInjection() throws Exception {
  String s="dataDir=${hbase.tmp.dir}/zookeeper\n" + "clientPort=2181\n" + "server.0=${hbase.master.hostname}:2888:3888\n";
  System.setProperty("hbase.master.hostname","localhost");
  InputStream is=new ByteArrayInputStream(s.getBytes());
  Properties properties=HQuorumPeer.parseZooCfg(conf,is);
  assertEquals(dataDir.toString(),properties.get("dataDir"));
  assertEquals(Integer.valueOf(2181),Integer.valueOf(properties.getProperty("clientPort")));
  assertEquals("localhost:2888:3888",properties.get("server.0"));
  QuorumPeerConfig config=new QuorumPeerConfig();
  config.parseProperties(properties);
  assertEquals(dataDir.toString(),config.getDataDir());
  assertEquals(2181,config.getClientPortAddress().getPort());
  Map<Long,QuorumServer> servers=config.getServers();
  assertEquals(1,servers.size());
  assertTrue(servers.containsKey(Long.valueOf(0)));
  QuorumServer server=servers.get(Long.valueOf(0));
  assertEquals("localhost",server.addr.getHostName());
  System.setProperty("hbase.master.hostname","foo.bar");
  is=new ByteArrayInputStream(s.getBytes());
  properties=HQuorumPeer.parseZooCfg(conf,is);
  assertEquals("foo.bar:2888:3888",properties.get("server.0"));
  config.parseProperties(properties);
  servers=config.getServers();
  server=servers.get(Long.valueOf(0));
  assertEquals("foo.bar",server.addr.getHostName());
}
