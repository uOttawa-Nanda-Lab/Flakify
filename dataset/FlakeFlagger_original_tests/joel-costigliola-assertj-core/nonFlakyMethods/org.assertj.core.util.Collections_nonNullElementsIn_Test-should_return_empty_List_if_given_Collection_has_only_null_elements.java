@Test public void should_return_empty_List_if_given_Collection_has_only_null_elements(){
  Collection<String> c=new ArrayList<>();
  c.add(null);
  assertTrue(Collections.nonNullElementsIn(c).isEmpty());
}
