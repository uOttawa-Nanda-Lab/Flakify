@Test public void testRangeSubSlicesQuery(){
  String cf="Super1";
  TestCleanupDescriptor cleanup=insertSuperColumns(cf,4,"testRangeSubSlicesQuery",3,"testRangeSubSlicesQuery");
  RangeSubSlicesQuery<String,String,String,String> q=createRangeSubSlicesQuery(ko,se,se,se,se);
  q.setColumnFamily(cf);
  q.setKeys("testRangeSubSlicesQuery2","testRangeSubSlicesQuery3");
  q.setSuperColumn("testRangeSubSlicesQuery1");
  q.setColumnNames("c021","c111");
  QueryResult<OrderedRows<String,String,String>> r=q.execute();
  assertNotNull(r);
  OrderedRows<String,String,String> rows=r.get();
  assertNotNull(rows);
  assertEquals(2,rows.getCount());
  Row<String,String,String> row=rows.getList().get(0);
  assertNotNull(row);
  assertEquals("testRangeSubSlicesQuery2",row.getKey());
  ColumnSlice<String,String> slice=row.getColumnSlice();
  assertNotNull(slice);
  assertEquals("v021",slice.getColumnByName("c021").getValue());
  assertEquals("v121",slice.getColumnByName("c111").getValue());
  assertNull(slice.getColumnByName("c033"));
  deleteColumns(cleanup);
}
