@Test public void should_return_existing_duplicates_array(){
  List<String[]> list=newArrayList(array("Merry"),array("Frodo"),new String[]{null},new String[]{null},array("Merry"),array("Sam"),array("Frodo"));
  Iterable<?> duplicates=standardComparisonStrategy.duplicatesFrom(list);
  assertTrue("must contains null",standardComparisonStrategy.iterableContains(duplicates,new String[]{null}));
  assertTrue("must contains Frodo",standardComparisonStrategy.iterableContains(duplicates,array("Frodo")));
  assertTrue("must contains Merry",standardComparisonStrategy.iterableContains(duplicates,array("Merry")));
  assertEquals(3,sizeOf(duplicates));
}
