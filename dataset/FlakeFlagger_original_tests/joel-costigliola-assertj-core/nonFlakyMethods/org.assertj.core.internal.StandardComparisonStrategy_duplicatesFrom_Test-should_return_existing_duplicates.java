@Test public void should_return_existing_duplicates(){
  List<String> list=newArrayList("Merry","Frodo",null,null,"Merry","Sam","Frodo");
  Iterable<?> duplicates=standardComparisonStrategy.duplicatesFrom(list);
  assertEquals(3,sizeOf(duplicates));
  assertTrue(standardComparisonStrategy.iterableContains(duplicates,"Frodo"));
  assertTrue(standardComparisonStrategy.iterableContains(duplicates,"Merry"));
  assertTrue(standardComparisonStrategy.iterableContains(duplicates,null));
}
