@Test public void testInsertGetRemove(){
  String cf="Standard1";
  Mutator<String> m=createMutator(ko,se);
  MutationResult mr=m.insert("testInsertGetRemove",cf,createColumn("testInsertGetRemove","testInsertGetRemove_value_",se,se));
  assertTrue("Time should be > 0",mr.getExecutionTimeMicro() > 0);
  log.debug("insert execution time: {}",mr.getExecutionTimeMicro());
  ColumnQuery<String,String,String> q=createColumnQuery(ko,se,se,se);
  q.setName("testInsertGetRemove").setColumnFamily(cf);
  QueryResult<HColumn<String,String>> r=q.setKey("testInsertGetRemove").execute();
  assertNotNull(r);
  HColumn<String,String> c=r.get();
  assertNotNull(c);
  String value=c.getValue();
  assertEquals("testInsertGetRemove_value_",value);
  String name=c.getName();
  assertEquals("testInsertGetRemove",name);
  assertEquals(q,r.getQuery());
  assertTrue("Time should be > 0",r.getExecutionTimeMicro() > 0);
  m=createMutator(ko,se);
  MutationResult mr2=m.delete("testInsertGetRemove",cf,"testInsertGetRemove",se);
  assertTrue("Time should be > 0",mr2.getExecutionTimeMicro() > 0);
  ColumnQuery<String,String,String> q2=createColumnQuery(ko,se,se,se);
  q2.setName("testInsertGetRemove").setColumnFamily(cf);
  QueryResult<HColumn<String,String>> r2=q2.setKey("testInsertGetRemove").execute();
  assertNotNull(r2);
  assertNull("Value should have been deleted",r2.get());
}
