/** 
 * Test a map/reduce against a multi-region table
 * @throws IOException
 * @throws ClassNotFoundException
 * @throws InterruptedException
 */
public void testMultiRegionTable() throws IOException, InterruptedException, ClassNotFoundException {
  runTestOnTable(new HTable(conf,MULTI_REGION_TABLE_NAME));
}
