@Test public void should_return_toString_of_Collection_of_Collections(){
  Collection<List<String>> collection=new ArrayList<>();
  collection.add(newArrayList("s1","s2"));
  collection.add(newArrayList("s3","s4","s5"));
  assertEquals("[[\"s1\", \"s2\"], [\"s3\", \"s4\", \"s5\"]]",new StandardRepresentation().toStringOf(collection));
}
