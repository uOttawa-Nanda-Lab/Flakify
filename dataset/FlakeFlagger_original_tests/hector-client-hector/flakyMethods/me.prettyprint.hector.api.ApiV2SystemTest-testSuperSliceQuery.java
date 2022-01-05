@Test public void testSuperSliceQuery(){
  String cf="Super1";
  Mutator<String> m=createMutator(ko,se);
  for (int j=1; j <= 3; ++j) {
    @SuppressWarnings("unchecked") HSuperColumn<String,String,String> sc=createSuperColumn("testSuperSliceQuery" + j,Arrays.asList(createColumn("name","value",se,se)),se,se,se);
    m.addInsertion("testSuperSliceQuery",cf,sc);
  }
  MutationResult mr=m.execute();
  assertTrue("Time should be > 0",mr.getExecutionTimeMicro() > 0);
  log.debug("insert execution time: {}",mr.getExecutionTimeMicro());
  SuperSliceQuery<String,String,String,String> q=createSuperSliceQuery(ko,se,se,se,se);
  q.setColumnFamily(cf);
  q.setKey("testSuperSliceQuery");
  q.setColumnNames("testSuperSliceQuery1","testSuperSliceQuery2","testSuperSliceQuery3");
  QueryResult<SuperSlice<String,String,String>> r=q.execute();
  assertNotNull(r);
  SuperSlice<String,String,String> slice=r.get();
  assertNotNull(slice);
  assertEquals(3,slice.getSuperColumns().size());
  assertEquals("value",slice.getColumnByName("testSuperSliceQuery1").getColumns().get(0).getValue());
  q=createSuperSliceQuery(ko,se,se,se,se);
  q.setColumnFamily(cf);
  q.setKey("testSuperSliceQuery");
  q.setRange("testSuperSliceQuery1","testSuperSliceQuery2",false,2);
  r=q.execute();
  assertNotNull(r);
  slice=r.get();
  assertNotNull(slice);
  for (  HSuperColumn<String,String,String> scolumn : slice.getSuperColumns()) {
    if (!scolumn.getName().equals("testSuperSliceQuery1") && !scolumn.getName().equals("testSuperSliceQuery2")) {
      fail("A columns with unexpected column name returned: " + scolumn.getName());
    }
  }
  for (int j=1; j <= 3; ++j) {
    m.addDeletion("testSuperSliceQuery",cf,"testSuperSliceQuery" + j,se);
  }
  mr=m.execute();
  r=q.execute();
  assertNotNull(r);
  slice=r.get();
  assertNotNull(slice);
  assertTrue(slice.getSuperColumns().isEmpty());
}
