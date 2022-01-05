@Test public void testNull() throws Exception {
  byte[] TABLE=Bytes.toBytes("testNull");
  try {
    TEST_UTIL.createTable(null,FAMILY);
    fail("Creating a table with null name passed, should have failed");
  }
 catch (  Exception e) {
  }
  try {
    TEST_UTIL.createTable(TABLE,(byte[])null);
    fail("Creating a table with a null family passed, should fail");
  }
 catch (  Exception e) {
  }
  HTable ht=TEST_UTIL.createTable(TABLE,FAMILY);
  try {
    Put put=new Put((byte[])null);
    put.add(FAMILY,QUALIFIER,VALUE);
    ht.put(put);
    fail("Inserting a null row worked, should throw exception");
  }
 catch (  Exception e) {
  }
{
    Put put=new Put(ROW);
    put.add(FAMILY,null,VALUE);
    ht.put(put);
    getTestNull(ht,ROW,FAMILY,VALUE);
    scanTestNull(ht,ROW,FAMILY,VALUE);
    Delete delete=new Delete(ROW);
    delete.deleteColumns(FAMILY,null);
    ht.delete(delete);
    Get get=new Get(ROW);
    Result result=ht.get(get);
    assertEmptyResult(result);
  }
  byte[] TABLE2=Bytes.toBytes("testNull2");
  ht=TEST_UTIL.createTable(TABLE2,FAMILY);
  try {
    Put put=new Put(ROW);
    put.add(FAMILY,HConstants.EMPTY_BYTE_ARRAY,VALUE);
    ht.put(put);
    getTestNull(ht,ROW,FAMILY,VALUE);
    scanTestNull(ht,ROW,FAMILY,VALUE);
    TEST_UTIL.flush();
    getTestNull(ht,ROW,FAMILY,VALUE);
    scanTestNull(ht,ROW,FAMILY,VALUE);
    Delete delete=new Delete(ROW);
    delete.deleteColumns(FAMILY,HConstants.EMPTY_BYTE_ARRAY);
    ht.delete(delete);
    Get get=new Get(ROW);
    Result result=ht.get(get);
    assertEmptyResult(result);
  }
 catch (  Exception e) {
    throw new IOException("Using a row with null qualifier threw exception, should ");
  }
  try {
    Put put=new Put(ROW);
    put.add(FAMILY,QUALIFIER,null);
    ht.put(put);
    Get get=new Get(ROW);
    get.addColumn(FAMILY,QUALIFIER);
    Result result=ht.get(get);
    assertSingleResult(result,ROW,FAMILY,QUALIFIER,null);
    Scan scan=new Scan();
    scan.addColumn(FAMILY,QUALIFIER);
    result=getSingleScanResult(ht,scan);
    assertSingleResult(result,ROW,FAMILY,QUALIFIER,null);
    Delete delete=new Delete(ROW);
    delete.deleteColumns(FAMILY,QUALIFIER);
    ht.delete(delete);
    get=new Get(ROW);
    result=ht.get(get);
    assertEmptyResult(result);
  }
 catch (  Exception e) {
    throw new IOException("Null values should be allowed, but threw exception");
  }
}
