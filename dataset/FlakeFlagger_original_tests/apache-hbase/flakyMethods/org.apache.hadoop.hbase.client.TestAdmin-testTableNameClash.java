/** 
 * Test for hadoop-1581 'HBASE: Unopenable tablename bug'.
 * @throws Exception
 */
@Test public void testTableNameClash() throws Exception {
  String name="testTableNameClash";
  admin.createTable(new HTableDescriptor(name + "SOMEUPPERCASE"));
  admin.createTable(new HTableDescriptor(name));
  new HTable(TEST_UTIL.getConfiguration(),name);
}
