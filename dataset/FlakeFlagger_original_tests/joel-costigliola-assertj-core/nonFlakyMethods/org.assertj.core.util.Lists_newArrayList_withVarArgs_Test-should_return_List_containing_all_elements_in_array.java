@Test public void should_return_List_containing_all_elements_in_array(){
  String[] expected={"One","Two"};
  ArrayList<String> list=Lists.newArrayList(expected);
  assertArrayEquals(expected,list.toArray());
}
