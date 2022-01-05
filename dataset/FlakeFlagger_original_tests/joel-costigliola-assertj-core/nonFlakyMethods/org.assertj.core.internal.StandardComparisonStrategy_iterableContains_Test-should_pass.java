@Test public void should_pass(){
  List<?> list=newArrayList("Sam","Merry",null,"Frodo");
  assertTrue(standardComparisonStrategy.iterableContains(list,"Frodo"));
  assertTrue(standardComparisonStrategy.iterableContains(list,null));
  assertFalse(standardComparisonStrategy.iterableContains(list,"Sauron"));
}
