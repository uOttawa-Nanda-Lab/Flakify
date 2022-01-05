@Test public void fruit1() throws Exception {
  List<FruitShell> fsList=doFirstPart("fruit1.xml");
  assertNotNull(fsList);
  assertEquals(1,fsList.size());
  FruitShell fs0=fsList.get(0);
  assertNotNull(fs0);
  assertEquals("fs0",fs0.getName());
  Fruit fruit0=fs0.fruitFactory.buildFruit();
  assertTrue(fruit0 instanceof Fruit);
  assertEquals("blue",fruit0.getName());
}
