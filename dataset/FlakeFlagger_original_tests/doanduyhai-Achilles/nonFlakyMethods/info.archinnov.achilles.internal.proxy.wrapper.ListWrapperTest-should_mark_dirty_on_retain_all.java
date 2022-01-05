@Test public void should_mark_dirty_on_retain_all() throws Exception {
  List<Object> target=Lists.<Object>newArrayList("a","b","c");
  ListWrapper wrapper=prepareListWrapper(target);
  wrapper.setProxifier(proxifier);
  Collection<String> list=Arrays.asList("a","c");
  when(proxifier.removeProxy(Mockito.<Collection<String>>any())).thenReturn(list);
  wrapper.retainAll(list);
  assertThat(target).containsOnly("a","c");
  DirtyChecker dirtyChecker=dirtyMap.get(setter);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(propertyMeta);
  DirtyCheckChangeSet changeSet=dirtyChecker.getChangeSets().get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(REMOVE_FROM_LIST);
  assertThat(changeSet.getPropertyMeta()).isEqualTo(propertyMeta);
  assertThat(changeSet.getRawListChanges()).containsOnly("b");
}
