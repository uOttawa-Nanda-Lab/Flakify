@Test public void should_mark_dirty_on_element_prepend() throws Exception {
  ArrayList<Object> target=new ArrayList<>();
  ListWrapper listWrapper=prepareListWrapper(target);
  when(proxifier.removeProxy("a")).thenReturn("a");
  listWrapper.add(0,"a");
  assertThat(target).hasSize(1);
  assertThat(target.get(0)).isEqualTo("a");
  DirtyChecker dirtyChecker=dirtyMap.get(setter);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(propertyMeta);
  DirtyCheckChangeSet changeSet=dirtyChecker.getChangeSets().get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(PREPEND_TO_LIST);
  assertThat(changeSet.getPropertyMeta()).isEqualTo(propertyMeta);
  assertThat(changeSet.getRawListChanges()).containsOnly("a");
}
