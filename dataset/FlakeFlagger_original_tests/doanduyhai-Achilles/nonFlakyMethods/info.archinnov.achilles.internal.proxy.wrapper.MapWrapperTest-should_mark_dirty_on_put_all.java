@Test public void should_mark_dirty_on_put_all() throws Exception {
  Map<Integer,String> target=prepareMap();
  MapWrapper wrapper=prepareMapWrapper(target);
  Map<Integer,String> map=new HashMap<Integer,String>();
  map.put(1,"FR");
  map.put(2,"Paris");
  when(proxifier.removeProxy("FR")).thenReturn("FR");
  when(proxifier.removeProxy("Paris")).thenReturn("Paris");
  wrapper.putAll(map);
  DirtyChecker dirtyChecker=dirtyMap.get(setter);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(propertyMeta);
  DirtyCheckChangeSet changeSet=dirtyChecker.getChangeSets().get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(ADD_TO_MAP);
  assertThat(changeSet.getPropertyMeta()).isEqualTo(propertyMeta);
  assertThat(changeSet.getRawMapChanges()).hasSize(2).contains(entry(1,"FR"),entry(2,"Paris"));
}
