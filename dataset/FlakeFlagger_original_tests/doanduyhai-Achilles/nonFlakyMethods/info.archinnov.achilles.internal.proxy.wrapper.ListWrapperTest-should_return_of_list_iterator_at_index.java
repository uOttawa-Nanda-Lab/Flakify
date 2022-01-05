@Test public void should_return_of_list_iterator_at_index() throws Exception {
  List<Object> target=Lists.<Object>newArrayList("a","b","c","d");
  ListWrapper listWrapper=prepareListWrapper(target);
  ListIterator<Object> listIterator=listWrapper.listIterator(1);
  listIterator.next();
  listIterator.remove();
  assertThat(target).containsExactly("a","b","c","d");
}
