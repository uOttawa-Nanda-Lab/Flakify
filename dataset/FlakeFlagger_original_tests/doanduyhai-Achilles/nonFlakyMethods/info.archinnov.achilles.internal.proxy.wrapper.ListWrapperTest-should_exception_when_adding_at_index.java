@Test(expected=UnsupportedOperationException.class) public void should_exception_when_adding_at_index() throws Exception {
  ArrayList<Object> target=new ArrayList<>();
  ListWrapper listWrapper=prepareListWrapper(target);
  when(proxifier.removeProxy("a")).thenReturn("a");
  listWrapper.add(1,"a");
}
