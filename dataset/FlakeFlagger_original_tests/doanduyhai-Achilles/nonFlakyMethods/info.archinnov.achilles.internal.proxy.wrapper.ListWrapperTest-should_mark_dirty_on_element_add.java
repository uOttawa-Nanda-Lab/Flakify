@Test public void should_mark_dirty_on_element_add() throws Exception {
  List<Object> target=new ArrayList<>();
  ListWrapper wrapper=prepareListWrapper(target);
  when(proxifier.removeProxy("a")).thenReturn("a");
  wrapper.add("a");
  assertThat(target).containsExactly("a");
  DirtyChecker dirtyChecker=dirtyMap.get(setter);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(propertyMeta);
  DirtyCheckChangeSet changeSet=dirtyChecker.getChangeSets().get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(APPEND_TO_LIST);
  assertThat(changeSet.getPropertyMeta()).isEqualTo(propertyMeta);
  assertThat(changeSet.getRawListChanges()).containsExactly("a");
}
