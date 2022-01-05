@Test public void should_mark_dirty_when_clear_on_full_map() throws Exception {
  Map<Integer,String> target=prepareMap();
  MapWrapper wrapper=prepareMapWrapper(target);
  wrapper.clear();
  assertThat(target).isEmpty();
  DirtyChecker dirtyChecker=dirtyMap.get(setter);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(propertyMeta);
  DirtyCheckChangeSet changeSet=dirtyChecker.getChangeSets().get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(REMOVE_COLLECTION_OR_MAP);
  assertThat(changeSet.getPropertyMeta()).isEqualTo(propertyMeta);
  assertThat(changeSet.getRawMapChanges()).isEmpty();
}
