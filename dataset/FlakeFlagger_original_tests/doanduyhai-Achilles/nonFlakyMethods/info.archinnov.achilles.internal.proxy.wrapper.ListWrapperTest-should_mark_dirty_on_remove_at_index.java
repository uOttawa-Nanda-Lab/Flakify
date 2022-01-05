@Test public void should_mark_dirty_on_remove_at_index() throws Exception {
  List<Object> target=Lists.<Object>newArrayList("a","b");
  ListWrapper listWrapper=prepareListWrapper(target);
  listWrapper.remove(1);
  assertThat(target).hasSize(1);
  assertThat(target.get(0)).isEqualTo("a");
  DirtyChecker dirtyChecker=dirtyMap.get(setter);
}
