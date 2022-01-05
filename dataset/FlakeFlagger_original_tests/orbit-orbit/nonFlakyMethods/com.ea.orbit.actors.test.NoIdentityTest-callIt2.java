@Test public void callIt2() throws ExecutionException, InterruptedException {
  OrbitStage stage1=createStage();
  final ISingularThing ref=stage1.getReference(ISingularThing.class,null);
  assertEquals("resp",ref.justRespond().join());
}
