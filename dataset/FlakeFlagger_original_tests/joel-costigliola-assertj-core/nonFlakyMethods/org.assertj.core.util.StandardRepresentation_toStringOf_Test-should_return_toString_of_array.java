@Test public void should_return_toString_of_array(){
  assertEquals("[\"s1\", \"s2\"]",new StandardRepresentation().toStringOf(array("s1","s2")));
}
