/** 
 * HBASE-1182 Scan for columns > some timestamp
 */
@Test public void testJiraTest1182() throws Exception {
  byte[] TABLE=Bytes.toBytes("testJiraTest1182");
  byte[][] VALUES=makeNAscii(VALUE,7);
  long[] STAMPS=makeStamps(7);
  HTable ht=TEST_UTIL.createTable(TABLE,FAMILY,10);
  Put put=new Put(ROW);
  put.add(FAMILY,QUALIFIER,STAMPS[0],VALUES[0]);
  put.add(FAMILY,QUALIFIER,STAMPS[1],VALUES[1]);
  put.add(FAMILY,QUALIFIER,STAMPS[2],VALUES[2]);
  put.add(FAMILY,QUALIFIER,STAMPS[3],VALUES[3]);
  put.add(FAMILY,QUALIFIER,STAMPS[4],VALUES[4]);
  put.add(FAMILY,QUALIFIER,STAMPS[5],VALUES[5]);
  ht.put(put);
  getVersionRangeAndVerifyGreaterThan(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,5);
  getVersionRangeAndVerifyGreaterThan(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,2,5);
  getVersionRangeAndVerifyGreaterThan(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,4,5);
  scanVersionRangeAndVerifyGreaterThan(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,5);
  scanVersionRangeAndVerifyGreaterThan(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,2,5);
  scanVersionRangeAndVerifyGreaterThan(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,4,5);
  TEST_UTIL.flush();
  getVersionRangeAndVerifyGreaterThan(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,5);
  getVersionRangeAndVerifyGreaterThan(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,2,5);
  getVersionRangeAndVerifyGreaterThan(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,4,5);
  scanVersionRangeAndVerifyGreaterThan(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,5);
  scanVersionRangeAndVerifyGreaterThan(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,2,5);
  scanVersionRangeAndVerifyGreaterThan(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,4,5);
}
