/** 
 * Test that user table names can contain '-' and '.' so long as they do not start with same. HBASE-771
 * @throws IOException
 */
@Test public void testTableNames() throws IOException {
  byte[][] illegalNames=new byte[][]{Bytes.toBytes("-bad"),Bytes.toBytes(".bad"),HConstants.ROOT_TABLE_NAME,HConstants.META_TABLE_NAME};
  for (int i=0; i < illegalNames.length; i++) {
    try {
      new HTableDescriptor(illegalNames[i]);
      throw new IOException("Did not detect '" + Bytes.toString(illegalNames[i]) + "' as an illegal user table name");
    }
 catch (    IllegalArgumentException e) {
    }
  }
  byte[] legalName=Bytes.toBytes("g-oo.d");
  try {
    new HTableDescriptor(legalName);
  }
 catch (  IllegalArgumentException e) {
    throw new IOException("Legal user table name: '" + Bytes.toString(legalName) + "' caused IllegalArgumentException: "+ e.getMessage());
  }
}
