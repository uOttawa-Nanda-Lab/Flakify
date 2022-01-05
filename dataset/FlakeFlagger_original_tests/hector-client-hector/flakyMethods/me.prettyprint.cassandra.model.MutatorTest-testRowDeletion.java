@Test public void testRowDeletion(){
  String cf="Standard1";
  long initialTime=keyspace.createClock();
  Mutator<String> m=createMutator(keyspace,se);
  for (int i=0; i < 5; i++) {
    m.addInsertion("key" + i,cf,createColumn("name","value" + i,se,se));
  }
  m.execute();
  m.addDeletion("key0",cf,null,se,(initialTime - 100));
  m.execute();
  QueryResult<HColumn<String,String>> columnResult=createColumnQuery(keyspace,se,se,se).setColumnFamily(cf).setKey("key0").setName("name").execute();
  assertEquals("value0",columnResult.get().getValue());
  for (int i=0; i < 5; i++) {
    m.addDeletion("key" + i,cf,null,se);
  }
  m.execute();
  columnResult=createColumnQuery(keyspace,se,se,se).setColumnFamily(cf).setKey("key0").setName("name").execute();
  assertNull(columnResult.get());
}
