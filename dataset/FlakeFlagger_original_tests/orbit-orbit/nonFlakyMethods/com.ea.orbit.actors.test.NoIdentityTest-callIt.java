@Test public void callIt() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  final ISingularThing ref=SingularThingFactory.getReference();
  assertEquals("resp",ref.justRespond().join());
}
