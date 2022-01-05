/** 
 * For HADOOP-2579
 * @throws IOException
 */
@Test(expected=TableNotFoundException.class) public void testTableNotFoundExceptionWithoutAnyTables() throws IOException {
  new HTable(TEST_UTIL.getConfiguration(),"testTableNotFoundExceptionWithoutAnyTables");
}
