@Test public void checkClearState() throws Exception {
  assertEquals(0,database.getCollection("ISomeMatch").count());
  OrbitStage stage1=createStage();
  ISomeMatch someMatch=stage1.getReference(ISomeMatch.class,"300");
  ISomePlayer somePlayer=stage1.getReference(ISomePlayer.class,"101");
  someMatch.addPlayer(somePlayer).get();
  assertEquals(1,database.getCollection("ISomeMatch").count());
  someMatch.delete().get();
  assertEquals(0,database.getCollection("ISomeMatch").count());
}
