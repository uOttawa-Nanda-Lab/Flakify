@Test public void checkWritesTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  assertEquals(0,fakeDatabase.values().size());
  ISomeMatch someMatch=stage1.getReference(ISomeMatch.class,"300");
  ISomePlayer somePlayer=stage1.getReference(ISomePlayer.class,"101");
  someMatch.addPlayer(somePlayer).get();
  assertTrue(fakeDatabase.values().size() > 0);
}
