@Test public void testMaxKeyValueSize() throws Exception {
  byte[] TABLE=Bytes.toBytes("testMaxKeyValueSize");
  Configuration conf=TEST_UTIL.getConfiguration();
  String oldMaxSize=conf.get("hbase.client.keyvalue.maxsize");
  HTable ht=TEST_UTIL.createTable(TABLE,FAMILY);
  byte[] value=new byte[4 * 1024 * 1024];
  Put put=new Put(ROW);
  put.add(FAMILY,QUALIFIER,value);
  ht.put(put);
  try {
    conf.setInt("hbase.client.keyvalue.maxsize",2 * 1024 * 1024);
    TABLE=Bytes.toBytes("testMaxKeyValueSize2");
    ht=TEST_UTIL.createTable(TABLE,FAMILY);
    put=new Put(ROW);
    put.add(FAMILY,QUALIFIER,value);
    ht.put(put);
    fail("Inserting a too large KeyValue worked, should throw exception");
  }
 catch (  Exception e) {
  }
  conf.set("hbase.client.keyvalue.maxsize",oldMaxSize);
}
