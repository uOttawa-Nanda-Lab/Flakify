@Test public void should_set_set_value() throws Throwable {
  Set<Object> set=newHashSet();
  Method method=CompleteBean.class.getDeclaredMethod("getFollowers");
  setterMetas.put(method,pm);
  when(pm.type()).thenReturn(PropertyType.SET);
  Object actual=interceptor.intercept(target,method,new Object[]{set},proxy);
  assertThat(actual).isNull();
  assertThat(alreadyLoaded).isEmpty();
  assertThat(dirtyMap).containsKey(method);
  DirtyChecker dirtyChecker=dirtyMap.get(method);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(pm);
  List<DirtyCheckChangeSet> changeSets=dirtyChecker.getChangeSets();
  assertThat(changeSets).hasSize(1);
  DirtyCheckChangeSet changeSet=changeSets.get(0);
  assertThat(changeSet.getChangeType()).isEqualTo(ASSIGN_VALUE_TO_SET);
  assertThat(changeSet.getRawSetChanges()).isSameAs(set);
  verify(pm,times(2)).setValueToField(target,set);
}
