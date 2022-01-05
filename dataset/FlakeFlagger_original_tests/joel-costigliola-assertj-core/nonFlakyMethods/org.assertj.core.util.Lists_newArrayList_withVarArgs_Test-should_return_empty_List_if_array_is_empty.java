@Test public void should_return_empty_List_if_array_is_empty(){
  ArrayList<Object> list=Lists.newArrayList(new Object[0]);
  assertTrue(list.isEmpty());
}
