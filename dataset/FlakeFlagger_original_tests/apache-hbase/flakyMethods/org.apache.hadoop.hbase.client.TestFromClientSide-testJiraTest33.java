/** 
 * HBASE-33 Add a HTable get/obtainScanner method that retrieves all versions of a particular column and row between two timestamps
 */
@Test public void testJiraTest33() throws Exception {
  byte[] TABLE=Bytes.toBytes("testJiraTest33");
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
  getVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,5);
  getVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,2);
  getVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,4,5);
  getVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,2,3);
  scanVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,5);
  scanVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,2);
  scanVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,4,5);
  scanVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,2,3);
  TEST_UTIL.flush();
  getVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,5);
  getVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,2);
  getVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,4,5);
  getVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,2,3);
  scanVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,5);
  scanVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,0,2);
  scanVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,4,5);
  scanVersionRangeAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS,VALUES,2,3);
}
