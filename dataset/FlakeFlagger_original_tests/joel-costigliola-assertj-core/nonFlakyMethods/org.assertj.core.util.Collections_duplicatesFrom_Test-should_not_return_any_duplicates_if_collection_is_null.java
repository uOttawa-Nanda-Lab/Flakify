@Test public void should_not_return_any_duplicates_if_collection_is_null(){
  Collection<String> duplicates=duplicatesFrom(null);
  assertTrue(duplicates.isEmpty());
}
