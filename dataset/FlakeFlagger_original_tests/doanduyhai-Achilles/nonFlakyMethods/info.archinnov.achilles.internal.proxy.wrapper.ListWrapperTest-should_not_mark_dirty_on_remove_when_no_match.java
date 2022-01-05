@Test public void should_not_mark_dirty_on_remove_when_no_match() throws Exception {
  List<Object> target=Lists.<Object>newArrayList("a","b");
  ListWrapper wrapper=prepareListWrapper(target);
  wrapper.remove("c");
  assertThat(dirtyMap).isEmpty();
}
