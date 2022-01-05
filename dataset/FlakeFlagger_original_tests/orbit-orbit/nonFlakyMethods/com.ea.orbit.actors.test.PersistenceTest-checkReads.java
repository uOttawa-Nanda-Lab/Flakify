@Test public void checkReads() throws ExecutionException, InterruptedException {
{
    OrbitStage stage1=createStage();
    assertEquals(0,fakeDatabase.values().size());
    ISomeMatch someMatch=stage1.getReference(ISomeMatch.class,"300");
    ISomePlayer somePlayer=stage1.getReference(ISomePlayer.class,"101");
    someMatch.addPlayer(somePlayer).get();
    assertTrue(fakeDatabase.values().size() > 0);
    stage1.stop();
  }
{
    OrbitStage stage2=createStage();
    ISomeMatch someMatch_r2=stage2.getReference(ISomeMatch.class,"300");
    ISomePlayer somePlayer_r2=stage2.getReference(ISomePlayer.class,"101");
    assertEquals(1,someMatch_r2.getPlayers().get().size());
    assertEquals(somePlayer_r2,someMatch_r2.getPlayers().get().get(0));
  }
}
