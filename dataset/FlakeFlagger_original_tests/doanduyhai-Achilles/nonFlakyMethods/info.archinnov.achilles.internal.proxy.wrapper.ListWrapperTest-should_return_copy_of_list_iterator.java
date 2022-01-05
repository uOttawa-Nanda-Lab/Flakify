@Test public void should_return_copy_of_list_iterator() throws Exception {
  List<Object> target=Lists.<Object>newArrayList("a","b","c");
  ListWrapper listWrapper=prepareListWrapper(target);
  ListIterator<Object> listIterator=listWrapper.listIterator();
  listIterator.next();
  listIterator.remove();
  assertThat(target).containsExactly("a","b","c");
}
