@Test public void should_mark_dirty_on_remove() throws Exception {
  Set<Object> target=Sets.<Object>newHashSet("a","b");
  SetWrapper wrapper=prepareSetWrapper(target);
  when(proxifier.removeProxy("a")).thenReturn("a");
  wrapper.remove("a");
  assertThat(target).containsExactly("b");
  DirtyChecker dirtyChecker=dirtyMap.get(setter);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(propertyMeta);
  DirtyCheckChangeSet changeSet=dirtyChecker.getChangeSets().get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(REMOVE_FROM_SET);
  assertThat(changeSet.getPropertyMeta()).isEqualTo(propertyMeta);
  assertThat(changeSet.getRawSetChanges()).containsOnly("a");
}
