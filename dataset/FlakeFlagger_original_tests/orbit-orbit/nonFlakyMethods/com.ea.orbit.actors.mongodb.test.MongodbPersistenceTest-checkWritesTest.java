@Test public void checkWritesTest() throws Exception {
  OrbitStage stage1=createStage();
  assertEquals(0,database.getCollection("ISomeMatch").count());
  ISomeMatch someMatch=stage1.getReference(ISomeMatch.class,"300");
  ISomePlayer somePlayer=stage1.getReference(ISomePlayer.class,"101");
  someMatch.addPlayer(somePlayer).get();
  assertEquals(1,database.getCollection("ISomeMatch").count());
}
