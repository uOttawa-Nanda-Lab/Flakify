@Test public void should_return_existing_duplicates(){
  Iterable<?> duplicates=caseInsensitiveComparisonStrategy.duplicatesFrom(newArrayList("Merry","Frodo","Merry","Sam","FrODO",null,null));
  assertEquals(3,sizeOf(duplicates));
  assertTrue(caseInsensitiveComparisonStrategy.iterableContains(duplicates,"frodo"));
  assertTrue(caseInsensitiveComparisonStrategy.iterableContains(duplicates,"MERRY"));
  assertTrue(caseInsensitiveComparisonStrategy.iterableContains(duplicates,null));
}
