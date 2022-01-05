@Test public void fruit2() throws Exception {
  List<FruitShell> fsList=doFirstPart("fruit2.xml");
  assertNotNull(fsList);
  assertEquals(2,fsList.size());
  FruitShell fs0=fsList.get(0);
  assertNotNull(fs0);
  assertEquals("fs0",fs0.getName());
  Fruit fruit0=fs0.fruitFactory.buildFruit();
  assertTrue(fruit0 instanceof Fruit);
  assertEquals("blue",fruit0.getName());
  FruitShell fs1=fsList.get(1);
  assertNotNull(fs1);
  assertEquals("fs1",fs1.getName());
  Fruit fruit1=fs1.fruitFactory.buildFruit();
  assertTrue(fruit1 instanceof WeightytFruit);
  assertEquals("orange",fruit1.getName());
  assertEquals(1.2,((WeightytFruit)fruit1).getWeight(),0.01);
}
