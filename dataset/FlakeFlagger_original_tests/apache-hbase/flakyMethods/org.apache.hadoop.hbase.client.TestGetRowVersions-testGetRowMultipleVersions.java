/** 
 * @throws Exception 
 */
public void testGetRowMultipleVersions() throws Exception {
  Put put=new Put(ROW,TIMESTAMP1,null);
  put.add(CONTENTS,CONTENTS,VALUE1);
  this.table.put(put);
  this.cluster.shutdown();
  this.zooKeeperCluster.shutdown();
  LOG.debug("HBase cluster shut down -- restarting");
  this.hBaseClusterSetup();
  this.table=new HTable(conf,TABLE_NAME);
  put=new Put(ROW,TIMESTAMP2,null);
  put.add(CONTENTS,CONTENTS,VALUE2);
  this.table.put(put);
  Get get=new Get(ROW);
  Result r=table.get(get);
  assertNotNull(r);
  assertFalse(r.isEmpty());
  assertTrue(r.size() == 1);
  byte[] value=r.getValue(CONTENTS,CONTENTS);
  assertTrue(value.length != 0);
  assertTrue(Bytes.equals(value,VALUE2));
  get=new Get(ROW);
  get.setMaxVersions();
  r=table.get(get);
  assertTrue(r.size() == 2);
  value=r.getValue(CONTENTS,CONTENTS);
  assertTrue(value.length != 0);
  assertTrue(Bytes.equals(value,VALUE2));
  NavigableMap<byte[],NavigableMap<byte[],NavigableMap<Long,byte[]>>> map=r.getMap();
  NavigableMap<byte[],NavigableMap<Long,byte[]>> familyMap=map.get(CONTENTS);
  NavigableMap<Long,byte[]> versionMap=familyMap.get(CONTENTS);
  assertTrue(versionMap.size() == 2);
  assertTrue(Bytes.equals(VALUE1,versionMap.get(TIMESTAMP1)));
  assertTrue(Bytes.equals(VALUE2,versionMap.get(TIMESTAMP2)));
}
