@Test public void should_not_return_any_duplicates(){
  Collection<String> duplicates=duplicatesFrom(asList("Frodo","Sam","Gandalf"));
  assertTrue(duplicates.isEmpty());
}
