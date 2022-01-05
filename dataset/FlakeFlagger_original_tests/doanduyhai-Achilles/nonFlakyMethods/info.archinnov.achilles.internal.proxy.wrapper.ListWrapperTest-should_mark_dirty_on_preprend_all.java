@Test public void should_mark_dirty_on_preprend_all() throws Exception {
  ArrayList<Object> target=new ArrayList<>();
  ListWrapper listWrapper=prepareListWrapper(target);
  listWrapper.setProxifier(proxifier);
  Collection<String> list=asList("b","c");
  when(proxifier.removeProxy(list)).thenReturn(list);
  listWrapper.addAll(0,list);
  assertThat(target).containsExactly("b","c");
  DirtyChecker dirtyChecker=dirtyMap.get(setter);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(propertyMeta);
  DirtyCheckChangeSet changeSet=dirtyChecker.getChangeSets().get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(PREPEND_TO_LIST);
  assertThat(changeSet.getPropertyMeta()).isEqualTo(propertyMeta);
  assertThat(changeSet.getRawListChanges()).containsExactly("b","c");
}
