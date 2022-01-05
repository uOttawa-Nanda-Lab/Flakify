@Test public void timeoutWithTwoMessagesTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  OrbitStage client=createClient();
  ISomeActor someActor=client.getReference(ISomeActor.class,"1");
  UUID uuid=someActor.getUniqueActivationId(0).get();
  assertEquals(uuid,someActor.getUniqueActivationId().get());
  Future<UUID> first=someActor.getUniqueActivationId(TimeUnit.SECONDS.toNanos(200));
  clock.incrementTimeMillis(TimeUnit.MINUTES.toMillis(60));
  Future<UUID> second=someActor.getUniqueActivationId(TimeUnit.SECONDS.toNanos(200));
  assertFalse(first.isDone());
  assertFalse(second.isDone());
  client.cleanup(false);
  assertTrue(first.isDone());
  assertFalse(second.isDone());
  clock.incrementTimeMillis(TimeUnit.MINUTES.toMillis(60));
  assertFalse(second.isDone());
  client.cleanup(false);
  assertTrue(second.isDone());
  expectException(() -> first.get());
  expectException(() -> second.get());
}
