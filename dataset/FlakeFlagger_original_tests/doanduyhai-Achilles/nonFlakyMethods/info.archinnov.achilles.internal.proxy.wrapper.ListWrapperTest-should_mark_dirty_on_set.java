@Test public void should_mark_dirty_on_set() throws Exception {
  List<Object> target=Lists.<Object>newArrayList("a","b","c");
  ListWrapper listWrapper=prepareListWrapper(target);
  when(proxifier.removeProxy("d")).thenReturn("d");
  listWrapper.set(1,"d");
  assertThat(target).hasSize(3);
  assertThat(target.get(1)).isEqualTo("d");
  DirtyChecker dirtyChecker=dirtyMap.get(setter);
}
