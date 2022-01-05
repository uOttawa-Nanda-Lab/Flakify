@Test public void should_set_list_value_to_null() throws Throwable {
  List<Object> list=null;
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
  assertThat(changeSet.getChangeType()).isEqualTo(REMOVE_COLLECTION_OR_MAP);
  assertThat(changeSet.getRawListChanges()).isEmpty();
  verify(pm,times(2)).setValueToField(target,list);
}
