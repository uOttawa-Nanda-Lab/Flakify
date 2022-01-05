@Test public void multipleActorsOfTheSameType() throws ExecutionException, InterruptedException {
  List<OrbitStage> stages=new ArrayList<>();
  for (int i=0; i < 10; i++) {
    stages.add(createStage());
  }
  Random r=new Random();
  List<ISomeActor> actors=new ArrayList<>();
  for (int i=0; i < 100; i++) {
    ISomeActor actor=stages.get(r.nextInt(stages.size())).getReference(ISomeActor.class,String.valueOf(i));
    actors.add(actor);
  }
  for (  ISomeActor actor : actors) {
    assertEquals("bla",actor.sayHello("bla").get());
  }
}
