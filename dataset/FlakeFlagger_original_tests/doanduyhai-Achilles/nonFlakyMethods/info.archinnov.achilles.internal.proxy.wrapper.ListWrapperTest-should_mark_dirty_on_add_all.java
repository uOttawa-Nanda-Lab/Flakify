@Test public void should_mark_dirty_on_add_all() throws Exception {
  List<Object> target=new ArrayList<>();
  ListWrapper wrapper=prepareListWrapper(target);
  Collection<String> list=asList("a","b");
  wrapper.setProxifier(proxifier);
  when(proxifier.removeProxy(Mockito.<Collection<String>>any())).thenReturn(list);
  wrapper.addAll(list);
  DirtyChecker dirtyChecker=dirtyMap.get(setter);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(propertyMeta);
  DirtyCheckChangeSet changeSet=dirtyChecker.getChangeSets().get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(APPEND_TO_LIST);
  assertThat(changeSet.getPropertyMeta()).isEqualTo(propertyMeta);
  assertThat(changeSet.getRawListChanges()).containsOnly("a","b");
}
