@Test public void should_not_return_any_duplicates(){
  Iterable<?> duplicates=caseInsensitiveComparisonStrategy.duplicatesFrom(newArrayList("Frodo","Sam","Gandalf"));
  assertTrue(isNullOrEmpty(duplicates));
}
