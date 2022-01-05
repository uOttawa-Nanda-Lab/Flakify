@Test public void withSubst() throws Exception {
  List<FruitShell> fsList=doFirstPart("fruitWithSubst.xml");
  assertNotNull(fsList);
  assertEquals(1,fsList.size());
  FruitShell fs0=fsList.get(0);
  assertNotNull(fs0);
  assertEquals("fs0",fs0.getName());
  int oldCount=FruitFactory.count;
  Fruit fruit0=fs0.fruitFactory.buildFruit();
  assertTrue(fruit0 instanceof WeightytFruit);
  assertEquals("orange-" + oldCount,fruit0.getName());
  assertEquals(1.2,((WeightytFruit)fruit0).getWeight(),0.01);
}
