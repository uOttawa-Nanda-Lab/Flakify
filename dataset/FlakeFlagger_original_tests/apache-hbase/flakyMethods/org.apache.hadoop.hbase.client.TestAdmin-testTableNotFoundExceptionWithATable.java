/** 
 * For HADOOP-2579
 * @throws IOException
 */
@Test(expected=TableExistsException.class) public void testTableNotFoundExceptionWithATable() throws IOException {
  final byte[] name=Bytes.toBytes("testTableNotFoundExceptionWithATable");
  TEST_UTIL.createTable(name,HConstants.CATALOG_FAMILY);
  TEST_UTIL.createTable(name,HConstants.CATALOG_FAMILY);
}
