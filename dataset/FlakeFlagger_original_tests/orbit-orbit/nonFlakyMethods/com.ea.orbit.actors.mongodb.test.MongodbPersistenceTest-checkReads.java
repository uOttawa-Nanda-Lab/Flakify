@Test public void checkReads() throws Exception {
  assertEquals(0,database.getCollection("ISomeMatch").count());
{
    OrbitStage stage1=createStage();
    ISomeMatch someMatch=stage1.getReference(ISomeMatch.class,"300");
    ISomePlayer somePlayer=stage1.getReference(ISomePlayer.class,"101");
    someMatch.addPlayer(somePlayer).get();
    stage1.stop();
  }
  assertEquals(1,database.getCollection("ISomeMatch").count());
{
    OrbitStage stage2=createStage();
    ISomeMatch someMatch_r2=stage2.getReference(ISomeMatch.class,"300");
    ISomePlayer somePlayer_r2=stage2.getReference(ISomePlayer.class,"101");
    assertEquals(1,someMatch_r2.getPlayers().get().size());
    assertEquals(somePlayer_r2,someMatch_r2.getPlayers().get().get(0));
  }
}
