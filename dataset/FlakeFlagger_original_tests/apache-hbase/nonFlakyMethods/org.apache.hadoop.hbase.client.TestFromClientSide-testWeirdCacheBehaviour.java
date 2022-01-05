/** 
 * Test from client side of an involved filter against a multi family that involves deletes.
 * @throws Exception
 */
@Test public void testWeirdCacheBehaviour() throws Exception {
  byte[] TABLE=Bytes.toBytes("testWeirdCacheBehaviour");
  byte[][] FAMILIES=new byte[][]{Bytes.toBytes("trans-blob"),Bytes.toBytes("trans-type"),Bytes.toBytes("trans-date"),Bytes.toBytes("trans-tags"),Bytes.toBytes("trans-group")};
  HTable ht=TEST_UTIL.createTable(TABLE,FAMILIES);
  String value="this is the value";
  String value2="this is some other value";
  String keyPrefix1=UUID.randomUUID().toString();
  String keyPrefix2=UUID.randomUUID().toString();
  String keyPrefix3=UUID.randomUUID().toString();
  putRows(ht,3,value,keyPrefix1);
  putRows(ht,3,value,keyPrefix2);
  putRows(ht,3,value,keyPrefix3);
  ht.flushCommits();
  putRows(ht,3,value2,keyPrefix1);
  putRows(ht,3,value2,keyPrefix2);
  putRows(ht,3,value2,keyPrefix3);
  HTable table=new HTable(TEST_UTIL.getConfiguration(),Bytes.toBytes("testWeirdCacheBehaviour"));
  System.out.println("Checking values for key: " + keyPrefix1);
  assertEquals("Got back incorrect number of rows from scan",3,getNumberOfRows(keyPrefix1,value2,table));
  System.out.println("Checking values for key: " + keyPrefix2);
  assertEquals("Got back incorrect number of rows from scan",3,getNumberOfRows(keyPrefix2,value2,table));
  System.out.println("Checking values for key: " + keyPrefix3);
  assertEquals("Got back incorrect number of rows from scan",3,getNumberOfRows(keyPrefix3,value2,table));
  deleteColumns(ht,value2,keyPrefix1);
  deleteColumns(ht,value2,keyPrefix2);
  deleteColumns(ht,value2,keyPrefix3);
  System.out.println("Starting important checks.....");
  assertEquals("Got back incorrect number of rows from scan: " + keyPrefix1,0,getNumberOfRows(keyPrefix1,value2,table));
  assertEquals("Got back incorrect number of rows from scan: " + keyPrefix2,0,getNumberOfRows(keyPrefix2,value2,table));
  assertEquals("Got back incorrect number of rows from scan: " + keyPrefix3,0,getNumberOfRows(keyPrefix3,value2,table));
  ht.setScannerCaching(0);
  assertEquals("Got back incorrect number of rows from scan",0,getNumberOfRows(keyPrefix1,value2,table));
  ht.setScannerCaching(100);
  assertEquals("Got back incorrect number of rows from scan",0,getNumberOfRows(keyPrefix2,value2,table));
}
