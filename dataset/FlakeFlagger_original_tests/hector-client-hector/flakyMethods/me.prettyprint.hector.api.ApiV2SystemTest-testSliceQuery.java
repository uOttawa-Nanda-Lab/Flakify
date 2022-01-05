@Test public void testSliceQuery(){
  String cf="Standard1";
  TestCleanupDescriptor cleanup=insertColumns(cf,1,"testSliceQuery",4,"testSliceQuery");
  SliceQuery<String,String,String> q=createSliceQuery(ko,se,se,se);
  q.setColumnFamily(cf);
  q.setKey("testSliceQuery0");
  q.setColumnNames("testSliceQuery1","testSliceQuery2","testSliceQuery3");
  QueryResult<ColumnSlice<String,String>> r=q.execute();
  assertNotNull(r);
  ColumnSlice<String,String> slice=r.get();
  assertNotNull(slice);
  assertEquals(3,slice.getColumns().size());
  assertEquals("value01",slice.getColumnByName("testSliceQuery1").getValue());
  assertEquals("value02",slice.getColumnByName("testSliceQuery2").getValue());
  assertEquals("value03",slice.getColumnByName("testSliceQuery3").getValue());
  List<HColumn<String,String>> columns=slice.getColumns();
  assertNotNull(columns);
  assertEquals(3,columns.size());
  q=createSliceQuery(ko,se,se,se);
  q.setColumnFamily(cf);
  q.setKey("testSliceQuery0");
  q.setRange("testSliceQuery2","testSliceQuery1",true,100);
  r=q.execute();
  assertNotNull(r);
  slice=r.get();
  assertNotNull(slice);
  assertEquals(2,slice.getColumns().size());
  for (  HColumn<String,String> column : slice.getColumns()) {
    if (!column.getName().equals("testSliceQuery1") && !column.getName().equals("testSliceQuery2")) {
      fail("A columns with unexpected column name returned: " + column.getName());
    }
  }
  deleteColumns(cleanup);
}
