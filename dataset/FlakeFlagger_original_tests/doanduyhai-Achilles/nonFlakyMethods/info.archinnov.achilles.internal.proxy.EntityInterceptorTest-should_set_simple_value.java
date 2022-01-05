@Test public void should_set_simple_value() throws Throwable {
  Method method=CompleteBean.class.getDeclaredMethod("getName");
  setterMetas.put(method,pm);
  when(pm.type()).thenReturn(PropertyType.SIMPLE);
  Object actual=interceptor.intercept(target,method,new Object[]{rawValue},proxy);
  assertThat(actual).isNull();
  assertThat(alreadyLoaded).isEmpty();
  assertThat(dirtyMap).containsKey(method);
  DirtyChecker dirtyChecker=dirtyMap.get(method);
  assertThat(dirtyChecker.getPropertyMeta()).isEqualTo(pm);
  assertThat(dirtyChecker.isSimpleField()).isTrue();
  verify(pm,times(2)).setValueToField(target,rawValue);
}
