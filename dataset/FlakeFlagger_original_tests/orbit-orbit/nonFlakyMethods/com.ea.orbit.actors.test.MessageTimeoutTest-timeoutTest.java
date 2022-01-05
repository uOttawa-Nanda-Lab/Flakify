@Test public void timeoutTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  OrbitStage client=createClient();
  ISomeActor someActor=client.getReference(ISomeActor.class,"1");
  UUID uuid=someActor.getUniqueActivationId(0).get();
  assertEquals(uuid,someActor.getUniqueActivationId().get());
  Future<UUID> call=someActor.getUniqueActivationId(TimeUnit.SECONDS.toNanos(200));
  clock.incrementTimeMillis(TimeUnit.MINUTES.toMillis(60));
  client.cleanup(false);
  assertTrue(call.isDone());
  expectException(() -> call.get());
}
