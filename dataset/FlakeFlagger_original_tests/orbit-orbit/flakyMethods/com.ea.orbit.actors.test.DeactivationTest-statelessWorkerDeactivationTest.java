@Test public void statelessWorkerDeactivationTest() throws ExecutionException, InterruptedException, TimeoutException {
  OrbitStage stage1=createStage();
  OrbitStage client=createClient();
  IStatelessThing actor5=client.getReference(IStatelessThing.class,"1000");
  final Set<UUID> set1=new HashSet<>();
{
    final List<Future<UUID>> futures=new ArrayList<>();
    for (int i=0; i < 100; i++) {
      futures.add(actor5.getUniqueActivationId(5000));
    }
    for (    Future<UUID> f : futures) {
      set1.add(f.get(10,TimeUnit.SECONDS));
    }
  }
  assertTrue(set1.size() > 1);
  assertTrue(set1.size() <= 100);
  clock.incrementTimeMillis(TimeUnit.MINUTES.toMillis(8));
  UUID theSurviving=actor5.getUniqueActivationId().get();
  clock.incrementTimeMillis(TimeUnit.MINUTES.toMillis(8));
  stage1.cleanup(true);
  final Set<UUID> set2=new HashSet<>();
{
    final List<Future<UUID>> futures=new ArrayList<>();
    for (int i=0; i < 25; i++) {
      futures.add(actor5.getUniqueActivationId(1000));
    }
    for (    Future<UUID> f : futures) {
      set2.add(f.get(10,TimeUnit.SECONDS));
    }
  }
  assertTrue(set2.size() > 1);
  assertTrue(set2.size() <= 100);
  assertTrue(set2.contains(theSurviving));
  set2.retainAll(set1);
  assertEquals(1,set2.size());
}
