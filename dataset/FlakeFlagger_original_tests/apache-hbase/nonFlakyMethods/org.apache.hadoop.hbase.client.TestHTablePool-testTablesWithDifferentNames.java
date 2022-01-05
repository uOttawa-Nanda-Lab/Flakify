@Test public void testTablesWithDifferentNames(){
  HTablePool pool=new HTablePool((HBaseConfiguration)null,Integer.MAX_VALUE);
  String tableName1="testTable1";
  String tableName2="testTable2";
  HTableInterface table1=pool.getTable(tableName1);
  HTableInterface table2=pool.getTable(tableName2);
  Assert.assertNotNull(table2);
  pool.putTable(table1);
  pool.putTable(table2);
  HTableInterface sameTable1=pool.getTable(tableName1);
  HTableInterface sameTable2=pool.getTable(tableName2);
  Assert.assertSame(table1,sameTable1);
  Assert.assertSame(table2,sameTable2);
}
