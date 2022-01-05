/** 
 * Tests the SubSliceQuery, a query on columns within a supercolumn
 */
@Test public void testSubSliceQuery(){
  String cf="Super1";
  TestCleanupDescriptor cleanup=insertSuperColumns(cf,1,"testSliceQueryOnSubcolumns",1,"testSliceQueryOnSubcolumns_column");
  SubSliceQuery<String,String,String,String> q=createSubSliceQuery(ko,se,se,se,se);
  q.setColumnFamily(cf);
  q.setSuperColumn("testSliceQueryOnSubcolumns_column0");
  q.setKey("testSliceQueryOnSubcolumns0");
  q.setColumnNames("c000","c110","c_doesn't_exist");
  QueryResult<ColumnSlice<String,String>> r=q.execute();
  assertNotNull(r);
  ColumnSlice<String,String> slice=r.get();
  assertNotNull(slice);
  assertEquals(2,slice.getColumns().size());
  assertEquals("v000",slice.getColumnByName("c000").getValue());
  q=createSubSliceQuery(ko,se,se,se,se);
  q.setColumnFamily(cf);
  q.setKey("testSliceQueryOnSubcolumns0");
  q.setSuperColumn("testSliceQueryOnSubcolumns_column0");
  q.setRange("c000","c110",false,2);
  r=q.execute();
  assertNotNull(r);
  slice=r.get();
  assertNotNull(slice);
  for (  HColumn<String,String> column : slice.getColumns()) {
    if (!column.getName().equals("c000") && !column.getName().equals("c110")) {
      fail("A columns with unexpected column name returned: " + column.getName());
    }
  }
  deleteColumns(cleanup);
  r=q.execute();
  assertNotNull(r);
  slice=r.get();
  assertNotNull(slice);
  assertTrue(slice.getColumns().isEmpty());
}
