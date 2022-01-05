/** 
 * HBASE-1014 commit(BatchUpdate) method should return timestamp
 */
@Test public void testJiraTest1014() throws Exception {
  byte[] TABLE=Bytes.toBytes("testJiraTest1014");
  HTable ht=TEST_UTIL.createTable(TABLE,FAMILY,10);
  long manualStamp=12345;
  Put put=new Put(ROW);
  put.add(FAMILY,QUALIFIER,manualStamp,VALUE);
  ht.put(put);
  getVersionAndVerify(ht,ROW,FAMILY,QUALIFIER,manualStamp,VALUE);
  getVersionAndVerifyMissing(ht,ROW,FAMILY,QUALIFIER,manualStamp - 1);
  getVersionAndVerifyMissing(ht,ROW,FAMILY,QUALIFIER,manualStamp + 1);
}
