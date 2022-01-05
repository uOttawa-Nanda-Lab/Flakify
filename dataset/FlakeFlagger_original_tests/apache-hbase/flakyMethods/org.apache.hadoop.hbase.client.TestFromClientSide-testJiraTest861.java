/** 
 * HBASE-861 get with timestamp will return a value if there is a version with an earlier timestamp
 */
@Test public void testJiraTest861() throws Exception {
  byte[] TABLE=Bytes.toBytes("testJiraTest861");
  byte[][] VALUES=makeNAscii(VALUE,7);
  long[] STAMPS=makeStamps(7);
  HTable ht=TEST_UTIL.createTable(TABLE,FAMILY,10);
  Put put=new Put(ROW);
  put.add(FAMILY,QUALIFIER,STAMPS[3],VALUES[3]);
  put.add(FAMILY,QUALIFIER,STAMPS[2],VALUES[2]);
  put.add(FAMILY,QUALIFIER,STAMPS[4],VALUES[4]);
  ht.put(put);
  getVersionAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS[2],VALUES[2]);
  getVersionAndVerifyMissing(ht,ROW,FAMILY,QUALIFIER,STAMPS[1]);
  getVersionAndVerifyMissing(ht,ROW,FAMILY,QUALIFIER,STAMPS[5]);
  TEST_UTIL.flush();
  getVersionAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS[2],VALUES[2]);
  getVersionAndVerifyMissing(ht,ROW,FAMILY,QUALIFIER,STAMPS[1]);
  getVersionAndVerifyMissing(ht,ROW,FAMILY,QUALIFIER,STAMPS[5]);
  put=new Put(ROW);
  put.add(FAMILY,QUALIFIER,STAMPS[0],VALUES[0]);
  put.add(FAMILY,QUALIFIER,STAMPS[6],VALUES[6]);
  ht.put(put);
  getVersionAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS[0],VALUES[0]);
  getVersionAndVerifyMissing(ht,ROW,FAMILY,QUALIFIER,STAMPS[1]);
  getVersionAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS[2],VALUES[2]);
  getVersionAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS[3],VALUES[3]);
  getVersionAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS[4],VALUES[4]);
  getVersionAndVerifyMissing(ht,ROW,FAMILY,QUALIFIER,STAMPS[5]);
  getVersionAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS[6],VALUES[6]);
  TEST_UTIL.flush();
  getVersionAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS[0],VALUES[0]);
  getVersionAndVerifyMissing(ht,ROW,FAMILY,QUALIFIER,STAMPS[1]);
  getVersionAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS[2],VALUES[2]);
  getVersionAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS[3],VALUES[3]);
  getVersionAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS[4],VALUES[4]);
  getVersionAndVerifyMissing(ht,ROW,FAMILY,QUALIFIER,STAMPS[5]);
  getVersionAndVerify(ht,ROW,FAMILY,QUALIFIER,STAMPS[6],VALUES[6]);
}
