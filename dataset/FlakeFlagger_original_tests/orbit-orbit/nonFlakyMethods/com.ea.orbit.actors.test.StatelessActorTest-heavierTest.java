/** 
 * Sends a bit more messages trying to uncover concurrency issues.
 */
@Test public void heavierTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  OrbitStage stage2=createStage();
  IStatelessThing actor1=stage1.getReference(IStatelessThing.class,"1000");
  IStatelessThing actor2=stage2.getReference(IStatelessThing.class,"1000");
  final Set<UUID> set=new HashSet<>();
  set.clear();
  List<Future<UUID>> futures=new ArrayList<>();
  for (int i=0; i < 50; i++) {
    futures.add(actor1.getUniqueActivationId());
    futures.add(actor2.getUniqueActivationId());
  }
  futures.forEach(f -> {
    try {
      set.add(f.get(10,TimeUnit.SECONDS));
    }
 catch (    Exception e) {
      throw new UncheckedException(e);
    }
  }
);
  assertTrue(set.size() > 1);
  assertTrue(set.size() <= 100);
}
