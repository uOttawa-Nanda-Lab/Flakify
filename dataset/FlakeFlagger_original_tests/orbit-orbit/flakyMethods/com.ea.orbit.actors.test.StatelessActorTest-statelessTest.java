@Test public void statelessTest() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  OrbitStage stage2=createStage();
  OrbitStage stage3=createStage();
  OrbitStage stage4=createStage();
  OrbitStage client=createClient();
  IStatelessThing actor1=stage1.getReference(IStatelessThing.class,"1000");
  IStatelessThing actor2=stage2.getReference(IStatelessThing.class,"1000");
  IStatelessThing actor3=stage3.getReference(IStatelessThing.class,"1000");
  IStatelessThing actor4=stage4.getReference(IStatelessThing.class,"1000");
  IStatelessThing actor5=client.getReference(IStatelessThing.class,"1000");
  final Set<UUID> set=new HashSet<>();
  for (int i=0; i < 25; i++) {
    set.add(actor1.getUniqueActivationId().join());
    set.add(actor2.getUniqueActivationId().join());
    set.add(actor3.getUniqueActivationId().join());
    set.add(actor4.getUniqueActivationId().join());
    set.add(actor5.getUniqueActivationId().join());
  }
  assertEquals(4,set.size());
  set.clear();
  List<Future<UUID>> futures=new ArrayList<>();
  for (int i=0; i < 50; i++) {
    futures.add(actor1.getUniqueActivationId(5000));
    futures.add(actor2.getUniqueActivationId(5000));
    futures.add(actor3.getUniqueActivationId(5000));
    futures.add(actor4.getUniqueActivationId(5000));
    futures.add(actor5.getUniqueActivationId(5000));
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
  final int size=set.size();
  assertTrue("Expecting >4 but was: " + size,size > 4);
  assertTrue("Expecting <=" + (50 * 5) + " but was: "+ size,size <= 50 * 5);
}
