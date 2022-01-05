@Test public void testMultigetSuperSliceQuery(){
  String cf="Super1";
  TestCleanupDescriptor cleanup=insertSuperColumns(cf,4,"testSuperMultigetSliceQueryKey",3,"testSuperMultigetSliceQuery");
  MultigetSuperSliceQuery<String,String,String,String> q=createMultigetSuperSliceQuery(ko,se,se,se,se);
  q.setColumnFamily(cf);
  q.setKeys("testSuperMultigetSliceQueryKey0","testSuperMultigetSliceQueryKey3");
  q.setColumnNames("testSuperMultigetSliceQuery1","testSuperMultigetSliceQuery2");
  QueryResult<SuperRows<String,String,String,String>> r=q.execute();
  assertNotNull(r);
  SuperRows<String,String,String,String> rows=r.get();
  assertNotNull(rows);
  assertEquals(2,rows.getCount());
  SuperRow<String,String,String,String> row=rows.getByKey("testSuperMultigetSliceQueryKey0");
  assertNotNull(row);
  assertEquals("testSuperMultigetSliceQueryKey0",row.getKey());
  SuperSlice<String,String,String> slice=row.getSuperSlice();
  assertNotNull(slice);
  assertEquals("v001",slice.getColumnByName("testSuperMultigetSliceQuery1").getColumns().get(0).getValue());
  assertNull(slice.getColumnByName("testSuperMultigetSliceQuery3"));
  deleteColumns(cleanup);
}
