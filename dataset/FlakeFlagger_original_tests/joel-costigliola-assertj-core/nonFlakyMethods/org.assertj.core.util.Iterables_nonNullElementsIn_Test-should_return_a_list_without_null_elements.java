@Test public void should_return_a_list_without_null_elements(){
  List<String> c=newArrayList("Frodo",null,"Sam",null);
  List<String> nonNull=Iterables.nonNullElementsIn(c);
  assertArrayEquals(new String[]{"Frodo","Sam"},nonNull.toArray());
}
