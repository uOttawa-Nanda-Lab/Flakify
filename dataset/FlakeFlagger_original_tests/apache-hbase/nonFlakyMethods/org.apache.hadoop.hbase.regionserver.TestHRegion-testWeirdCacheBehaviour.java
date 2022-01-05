/** 
 * An involved filter test.  Has multiple column families and deletes in mix.
 */
public void testWeirdCacheBehaviour() throws Exception {
  byte[] TABLE=Bytes.toBytes("testWeirdCacheBehaviour");
  byte[][] FAMILIES=new byte[][]{Bytes.toBytes("trans-blob"),Bytes.toBytes("trans-type"),Bytes.toBytes("trans-date"),Bytes.toBytes("trans-tags"),Bytes.toBytes("trans-group")};
  initHRegion(TABLE,getName(),FAMILIES);
  String value="this is the value";
  String value2="this is some other value";
  String keyPrefix1="prefix1";
  String keyPrefix2="prefix2";
  String keyPrefix3="prefix3";
  putRows(this.region,3,value,keyPrefix1);
  putRows(this.region,3,value,keyPrefix2);
  putRows(this.region,3,value,keyPrefix3);
  putRows(this.region,3,value2,keyPrefix1);
  putRows(this.region,3,value2,keyPrefix2);
  putRows(this.region,3,value2,keyPrefix3);
  System.out.println("Checking values for key: " + keyPrefix1);
  assertEquals("Got back incorrect number of rows from scan",3,getNumberOfRows(keyPrefix1,value2,this.region));
  System.out.println("Checking values for key: " + keyPrefix2);
  assertEquals("Got back incorrect number of rows from scan",3,getNumberOfRows(keyPrefix2,value2,this.region));
  System.out.println("Checking values for key: " + keyPrefix3);
  assertEquals("Got back incorrect number of rows from scan",3,getNumberOfRows(keyPrefix3,value2,this.region));
  deleteColumns(this.region,value2,keyPrefix1);
  deleteColumns(this.region,value2,keyPrefix2);
  deleteColumns(this.region,value2,keyPrefix3);
  System.out.println("Starting important checks.....");
  assertEquals("Got back incorrect number of rows from scan: " + keyPrefix1,0,getNumberOfRows(keyPrefix1,value2,this.region));
  assertEquals("Got back incorrect number of rows from scan: " + keyPrefix2,0,getNumberOfRows(keyPrefix2,value2,this.region));
  assertEquals("Got back incorrect number of rows from scan: " + keyPrefix3,0,getNumberOfRows(keyPrefix3,value2,this.region));
}
