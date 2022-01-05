@Test public void should_pass(){
  List<?> list=newArrayList("Sam","Merry",null,"Frodo");
  assertTrue(list.contains("Frodo"));
  standardComparisonStrategy.iterableRemoves(list,"Frodo");
  assertFalse(list.contains("Frodo"));
  standardComparisonStrategy.iterableRemoves(list,null);
  assertFalse(list.contains(null));
}
