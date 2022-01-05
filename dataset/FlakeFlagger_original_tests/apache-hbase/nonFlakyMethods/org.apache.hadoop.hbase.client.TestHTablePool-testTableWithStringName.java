@Test public void testTableWithStringName(){
  HTablePool pool=new HTablePool((HBaseConfiguration)null,Integer.MAX_VALUE);
  String tableName="testTable";
  HTableInterface table=pool.getTable(tableName);
  Assert.assertNotNull(table);
  pool.putTable(table);
  HTableInterface sameTable=pool.getTable(tableName);
  Assert.assertSame(table,sameTable);
}
