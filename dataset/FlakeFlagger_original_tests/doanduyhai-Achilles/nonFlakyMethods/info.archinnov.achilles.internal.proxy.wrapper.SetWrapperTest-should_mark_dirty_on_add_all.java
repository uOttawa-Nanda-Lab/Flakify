@Test public void should_mark_dirty_on_add_all() throws Exception {
  Set<Object> target=new HashSet<>();
  SetWrapper wrapper=prepareSetWrapper(target);
  Collection<String> list=Arrays.asList("a","b");
  wrapper.setProxifier(proxifier);
  when(proxifier.removeProxy(Mockito.<Collection<String>>any())).thenReturn(list);
  wrapper.addAll(list);
  DirtyChecker dirtyChecker=dirtyMap.get(setter);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(propertyMeta);
  DirtyCheckChangeSet changeSet=dirtyChecker.getChangeSets().get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(ADD_TO_SET);
  assertThat(changeSet.getPropertyMeta()).isEqualTo(propertyMeta);
  assertThat(changeSet.getRawSetChanges()).containsOnly("a","b");
}
