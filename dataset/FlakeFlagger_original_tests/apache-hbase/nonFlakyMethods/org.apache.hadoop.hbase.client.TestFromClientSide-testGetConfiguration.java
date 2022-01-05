/** 
 * Verifies that getConfiguration returns the same Configuration object used to create the HTable instance.
 */
@Test public void testGetConfiguration() throws Exception {
  byte[] TABLE=Bytes.toBytes("testGetConfiguration");
  byte[][] FAMILIES=new byte[][]{Bytes.toBytes("foo")};
  Configuration conf=TEST_UTIL.getConfiguration();
  HTable table=TEST_UTIL.createTable(TABLE,FAMILIES);
  assertSame(conf,table.getConfiguration());
}
