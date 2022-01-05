@Test public void should_not_mark_dirty_on_sub_list_add() throws Exception {
  List<Object> target=Lists.<Object>newArrayList("a","b","c");
  List<Object> subListWrapper=prepareListWrapper(target).subList(0,1);
  when(proxifier.removeProxy("d")).thenReturn("d");
  subListWrapper.add("d");
  assertThat(dirtyMap).isEmpty();
}
