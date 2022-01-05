@Test public void should_not_return_any_duplicates_if_collection_is_empty(){
  Collection<String> duplicates=duplicatesFrom(new ArrayList<String>());
  assertTrue(duplicates.isEmpty());
}
