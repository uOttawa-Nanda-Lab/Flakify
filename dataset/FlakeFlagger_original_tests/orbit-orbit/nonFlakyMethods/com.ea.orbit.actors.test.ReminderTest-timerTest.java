@Test public void timerTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  OrbitStage frontend=createClient();
  IReminderTestActor actor=frontend.getReference(IReminderTestActor.class,"1");
  actor.addReminder("bla",0,50,TimeUnit.MILLISECONDS).join();
  assertEquals("bla",remindersReceived.poll(5,TimeUnit.SECONDS));
  assertEquals("bla",remindersReceived.poll(5,TimeUnit.SECONDS));
}
