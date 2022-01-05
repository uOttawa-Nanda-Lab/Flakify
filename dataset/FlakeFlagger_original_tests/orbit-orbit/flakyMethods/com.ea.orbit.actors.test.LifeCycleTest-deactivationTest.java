@Test public void deactivationTest() throws ExecutionException, InterruptedException {
  OrbitStage stage=createStage();
  OrbitStage client=createClient();
  ISomeMatch match=client.getReference(ISomeMatch.class,"1000");
  ISomePlayer player=client.getReference(ISomePlayer.class,"101");
  match.addPlayer(player).get();
  int machEventCount=player.getMatchEventCount().get();
  clock.incrementTimeMillis(TimeUnit.MINUTES.toMillis(60));
  assertEquals(machEventCount,player.getMatchEventCount().get().intValue());
  stage.cleanup(true);
  assertNotEquals(machEventCount,player.getMatchEventCount().get().intValue());
}
