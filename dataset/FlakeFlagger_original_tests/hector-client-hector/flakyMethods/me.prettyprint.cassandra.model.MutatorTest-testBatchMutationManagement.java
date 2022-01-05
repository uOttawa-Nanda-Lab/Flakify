@Test public void testBatchMutationManagement(){
  String cf="Standard1";
  Mutator<String> m=createMutator(keyspace,se);
  for (int i=0; i < 5; i++) {
    m.addInsertion("k" + i,cf,createColumn("name","value" + i,se,se));
  }
  MutationResult r=m.execute();
  assertTrue("Execute time should be > 0",r.getExecutionTimeMicro() > 0);
  assertTrue("Should have operated on a host",r.getHostUsed() != null);
  for (int i=0; i < 5; i++) {
    assertColumnExists("Keyspace1","Standard1","k" + i,"name");
  }
  r=m.execute();
  assertEquals("Execute time should be 0",0,r.getExecutionTimeMicro());
  for (int i=0; i < 5; i++) {
    m.addInsertion("k" + i,cf,createColumn("name","value" + i,se,se));
  }
  m.discardPendingMutations();
  r=m.execute();
  assertEquals("Execute time should be 0",0,r.getExecutionTimeMicro());
  assertTrue("Should have operated with a null host",r.getHostUsed() == null);
  for (int i=0; i < 5; i++) {
    m.addDeletion("k" + i,cf,"name",se);
  }
  m.execute();
}
