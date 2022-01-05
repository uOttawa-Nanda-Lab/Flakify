@Test public void should_mark_dirty_on_clear() throws Exception {
  Set<Object> target=Sets.<Object>newHashSet("a");
  SetWrapper wrapper=prepareSetWrapper(target);
  wrapper.clear();
  assertThat(target).hasSize(0);
  DirtyChecker dirtyChecker=dirtyMap.get(setter);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(propertyMeta);
  DirtyCheckChangeSet changeSet=dirtyChecker.getChangeSets().get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(REMOVE_COLLECTION_OR_MAP);
  assertThat(changeSet.getPropertyMeta()).isEqualTo(propertyMeta);
  assertThat(changeSet.getRawSetChanges()).isEmpty();
}
