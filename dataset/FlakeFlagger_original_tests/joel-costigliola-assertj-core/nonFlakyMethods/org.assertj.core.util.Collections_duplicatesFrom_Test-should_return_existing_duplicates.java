@Test public void should_return_existing_duplicates(){
  Collection<String> duplicates=duplicatesFrom(asList("Merry","Frodo","Merry","Sam","Frodo"));
  assertArrayEquals(new String[]{"Merry","Frodo"},duplicates.toArray());
}
