@Test public void testCountQuery(){
  String cf="Standard1";
  TestCleanupDescriptor cleanup=insertColumns(cf,1,"testCountQuery",10,"testCountQueryColumn");
  CountQuery<String,String> cq=createCountQuery(ko,se,se);
  cq.setColumnFamily(cf).setKey("testCountQuery0");
  cq.setRange("testCountQueryColumn","testCountQueryColumn999",100);
  QueryResult<Integer> r=cq.execute();
  assertNotNull(r);
  assertEquals(Integer.valueOf(10),r.get());
  deleteColumns(cleanup);
  cq=createCountQuery(ko,se,se);
  cq.setColumnFamily(cf).setKey("testCountQuery_nonexisting");
  cq.setRange("testCountQueryColumn","testCountQueryColumn999",100);
  r=cq.execute();
  assertNotNull(r);
  assertEquals(Integer.valueOf(0),r.get());
}
