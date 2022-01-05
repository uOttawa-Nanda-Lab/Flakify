/** 
 * Test Case for HBASE-2305
 */
public void testShouldAssignDefaultZookeeperClientPort(){
  Configuration config=HBaseConfiguration.create();
  config.clear();
  Properties p=HQuorumPeer.makeZKProps(config);
  assertNotNull(p);
  assertEquals(2181,p.get("hbase.zookeeper.property.clientPort"));
}
