@Test public void should_not_mark_dirty_on_remove_all_when_no_match() throws Exception {
  List<Object> target=Lists.<Object>newArrayList("a","b","c");
  ListWrapper wrapper=prepareListWrapper(target);
  wrapper.removeAll(Arrays.asList("d","e"));
  assertThat(dirtyMap).isEmpty();
}
