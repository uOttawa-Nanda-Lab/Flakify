@Test public void should_mark_dirty_on_element_add() throws Exception {
  Set<Object> target=new HashSet<>();
  SetWrapper wrapper=prepareSetWrapper(target);
  when(proxifier.removeProxy("a")).thenReturn("a");
  wrapper.add("a");
  assertThat(target).containsExactly("a");
  DirtyChecker dirtyChecker=dirtyMap.get(setter);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(propertyMeta);
  DirtyCheckChangeSet changeSet=dirtyChecker.getChangeSets().get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(ADD_TO_SET);
  assertThat(changeSet.getPropertyMeta()).isEqualTo(propertyMeta);
  assertThat(changeSet.getRawSetChanges()).containsExactly("a");
}
