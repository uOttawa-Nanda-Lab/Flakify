@Test public void should_not_mark_dirty_on_list_iterator_add() throws Exception {
  List<Object> target=Lists.<Object>newArrayList("a","b","c");
  ListIterator<Object> listIteratorWrapper=prepareListWrapper(target).listIterator();
  when(proxifier.removeProxy("c")).thenReturn("c");
  listIteratorWrapper.add("c");
  assertThat(dirtyMap).isEmpty();
}
