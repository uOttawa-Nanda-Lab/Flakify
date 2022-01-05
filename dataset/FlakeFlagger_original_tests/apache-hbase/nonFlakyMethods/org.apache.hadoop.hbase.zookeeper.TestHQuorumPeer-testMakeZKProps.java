/** 
 */
public void testMakeZKProps(){
  Properties properties=HQuorumPeer.makeZKProps(conf);
  assertEquals(dataDir.toString(),properties.get("dataDir"));
  assertEquals(Integer.valueOf(21810),Integer.valueOf(properties.getProperty("clientPort")));
  assertEquals("localhost:2888:3888",properties.get("server.0"));
  assertEquals(null,properties.get("server.1"));
  String oldValue=conf.get(HConstants.ZOOKEEPER_QUORUM);
  conf.set(HConstants.ZOOKEEPER_QUORUM,"a.foo.bar,b.foo.bar,c.foo.bar");
  properties=HQuorumPeer.makeZKProps(conf);
  assertEquals(dataDir.toString(),properties.get("dataDir"));
  assertEquals(Integer.valueOf(21810),Integer.valueOf(properties.getProperty("clientPort")));
  assertEquals("a.foo.bar:2888:3888",properties.get("server.0"));
  assertEquals("b.foo.bar:2888:3888",properties.get("server.1"));
  assertEquals("c.foo.bar:2888:3888",properties.get("server.2"));
  assertEquals(null,properties.get("server.3"));
  conf.set(HConstants.ZOOKEEPER_QUORUM,oldValue);
}
