@Test public void should_set_map_value_to_null() throws Throwable {
  Map<Object,Object> map=null;
  Method method=CompleteBean.class.getDeclaredMethod("getPreferences");
  setterMetas.put(method,pm);
  when(pm.type()).thenReturn(PropertyType.SET);
  Object actual=interceptor.intercept(target,method,new Object[]{map},proxy);
  assertThat(actual).isNull();
  assertThat(alreadyLoaded).isEmpty();
  assertThat(dirtyMap).containsKey(method);
  DirtyChecker dirtyChecker=dirtyMap.get(method);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(pm);
  List<DirtyCheckChangeSet> changeSets=dirtyChecker.getChangeSets();
  assertThat(changeSets).hasSize(1);
  DirtyCheckChangeSet changeSet=changeSets.get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(REMOVE_COLLECTION_OR_MAP);
  assertThat(changeSet.getRawMapChanges()).isEmpty();
  verify(pm,times(2)).setValueToField(target,map);
}
