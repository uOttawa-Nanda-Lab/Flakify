@Test public void testInsertSuper(){
  Mutator<String> m=createMutator(keyspace,se);
  List<HColumn<String,String>> columnList=new ArrayList<HColumn<String,String>>();
  columnList.add(createColumn("name","value",se,se));
  HSuperColumn<String,String,String> superColumn=createSuperColumn("super_name",columnList,se,se,se);
  MutationResult r=m.insert("sk","Super1",superColumn);
  assertTrue("Execute time should be > 0",r.getExecutionTimeMicro() > 0);
  assertTrue("Should have operated on a host",r.getHostUsed() != null);
}
