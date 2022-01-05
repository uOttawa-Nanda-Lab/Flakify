@Test public void should_return_null_for_list_property() throws Throwable {
  Method method=CompleteBean.class.getDeclaredMethod("getFriends");
  Field field=CompleteBean.class.getDeclaredField("friends");
  setterMetas.put(method,pm);
  when(pm.type()).thenReturn(PropertyType.LIST);
  getterMetas.put(method,pm);
  when(invoker.getValueFromField(target,field)).thenReturn(null);
  Object actual=interceptor.intercept(target,method,args,proxy);
  assertThat(actual).isNull();
}
