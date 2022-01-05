@Test public void should_mark_dirty_on_put() throws Exception {
  Map<Integer,String> target=prepareMap();
  MapWrapper wrapper=prepareMapWrapper(target);
  wrapper.put(4,"sdfs");
  DirtyChecker dirtyChecker=dirtyMap.get(setter);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(propertyMeta);
  DirtyCheckChangeSet changeSet=dirtyChecker.getChangeSets().get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(ADD_TO_MAP);
  assertThat(changeSet.getPropertyMeta()).isEqualTo(propertyMeta);
  assertThat(changeSet.getRawMapChanges()).hasSize(1).containsKey(4).containsValue("sdfs");
}
