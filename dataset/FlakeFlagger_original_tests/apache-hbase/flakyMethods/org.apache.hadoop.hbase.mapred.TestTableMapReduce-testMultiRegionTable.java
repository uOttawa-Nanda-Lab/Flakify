/** 
 * Test a map/reduce against a multi-region table
 * @throws IOException
 */
public void testMultiRegionTable() throws IOException {
  runTestOnTable(new HTable(conf,MULTI_REGION_TABLE_NAME));
}
