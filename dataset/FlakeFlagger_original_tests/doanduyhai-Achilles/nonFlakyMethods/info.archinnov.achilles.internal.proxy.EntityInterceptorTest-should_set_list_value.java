@Test public void should_set_list_value() throws Throwable {
  List<Object> list=asList();
  Method method=CompleteBean.class.getDeclaredMethod("getFriends");
  setterMetas.put(method,pm);
  when(pm.type()).thenReturn(PropertyType.LIST);
  Object actual=interceptor.intercept(target,method,new Object[]{list},proxy);
  assertThat(actual).isNull();
  assertThat(alreadyLoaded).isEmpty();
  assertThat(dirtyMap).containsKey(method);
  DirtyChecker dirtyChecker=dirtyMap.get(method);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(pm);
  List<DirtyCheckChangeSet> changeSets=dirtyChecker.getChangeSets();
  assertThat(changeSets).hasSize(1);
  DirtyCheckChangeSet changeSet=changeSets.get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(ASSIGN_VALUE_TO_LIST);
  assertThat(changeSet.getRawListChanges()).isSameAs(list);
  verify(pm,times(2)).setValueToField(target,list);
}
