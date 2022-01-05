@Test public void should_return_Set_containing_all_elements_in_array(){
  String[] expected={"One","Two"};
  LinkedHashSet<String> set=Sets.newLinkedHashSet(expected);
  assertArrayEquals(expected,set.toArray());
}
