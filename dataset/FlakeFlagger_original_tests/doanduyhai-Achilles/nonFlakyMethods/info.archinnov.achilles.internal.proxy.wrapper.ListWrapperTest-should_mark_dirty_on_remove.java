@Test public void should_mark_dirty_on_remove() throws Exception {
  List<Object> target=Lists.<Object>newArrayList("a","b");
  ListWrapper wrapper=prepareListWrapper(target);
  when(proxifier.removeProxy("a")).thenReturn("a");
  wrapper.remove("a");
  assertThat(target).containsExactly("b");
  DirtyChecker dirtyChecker=dirtyMap.get(setter);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(propertyMeta);
  DirtyCheckChangeSet changeSet=dirtyChecker.getChangeSets().get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(REMOVE_FROM_LIST);
  assertThat(changeSet.getPropertyMeta()).isEqualTo(propertyMeta);
  assertThat(changeSet.getRawListChanges()).containsOnly("a");
}
