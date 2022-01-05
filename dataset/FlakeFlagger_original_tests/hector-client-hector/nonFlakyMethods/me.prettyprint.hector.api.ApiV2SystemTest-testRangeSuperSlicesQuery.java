@Test public void testRangeSuperSlicesQuery(){
  String cf="Super1";
  TestCleanupDescriptor cleanup=insertSuperColumns(cf,4,"testRangeSuperSlicesQuery",3,"testRangeSuperSlicesQuery");
  RangeSuperSlicesQuery<String,String,String,String> q=createRangeSuperSlicesQuery(ko,se,se,se,se);
  q.setColumnFamily(cf);
  q.setKeys("testRangeSuperSlicesQuery2","testRangeSuperSlicesQuery3");
  q.setColumnNames("testRangeSuperSlicesQuery1","testRangeSuperSlicesQuery2");
  QueryResult<OrderedSuperRows<String,String,String,String>> r=q.execute();
  assertNotNull(r);
  OrderedSuperRows<String,String,String,String> rows=r.get();
  assertNotNull(rows);
  assertEquals(2,rows.getCount());
  SuperRow<String,String,String,String> row=rows.getList().get(0);
  assertNotNull(row);
  assertEquals("testRangeSuperSlicesQuery2",row.getKey());
  SuperSlice<String,String,String> slice=row.getSuperSlice();
  assertNotNull(slice);
  assertEquals("v021",slice.getColumnByName("testRangeSuperSlicesQuery1").get(0).getValue());
  assertEquals("v022",slice.getColumnByName("testRangeSuperSlicesQuery2").get(0).getValue());
  assertNull(slice.getColumnByName("testRangeSuperSlicesQuery3"));
  q.setKeys("testRangeSuperSlicesQuery0","testRangeSuperSlicesQuery5");
  q.setRange("testRangeSuperSlicesQuery1","testRangeSuperSlicesQuery3",false,100);
  r=q.execute();
  assertNotNull(r);
  rows=r.get();
  assertEquals(4,rows.getCount());
  for (  SuperRow<String,String,String,String> row2 : rows) {
    assertNotNull(row2);
    slice=row2.getSuperSlice();
    assertNotNull(slice);
    assertEquals(2,slice.getSuperColumns().size());
    for (    HSuperColumn<String,String,String> column : slice.getSuperColumns()) {
      if (!column.getName().equals("testRangeSuperSlicesQuery1") && !column.getName().equals("testRangeSuperSlicesQuery2")) {
        fail("A columns with unexpected column name returned: " + column.getName());
      }
    }
  }
  deleteColumns(cleanup);
}
