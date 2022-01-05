@Test public void testTableWithMaxSize(){
  HTablePool pool=new HTablePool((HBaseConfiguration)null,2);
  String tableName="testTable";
  HTableInterface table1=pool.getTable(tableName);
  HTableInterface table2=pool.getTable(tableName);
  HTableInterface table3=pool.getTable(tableName);
  pool.putTable(table1);
  pool.putTable(table2);
  pool.putTable(table3);
  HTableInterface sameTable1=pool.getTable(tableName);
  HTableInterface sameTable2=pool.getTable(tableName);
  HTableInterface sameTable3=pool.getTable(tableName);
  Assert.assertSame(table1,sameTable1);
  Assert.assertSame(table2,sameTable2);
  Assert.assertNotSame(table3,sameTable3);
}
