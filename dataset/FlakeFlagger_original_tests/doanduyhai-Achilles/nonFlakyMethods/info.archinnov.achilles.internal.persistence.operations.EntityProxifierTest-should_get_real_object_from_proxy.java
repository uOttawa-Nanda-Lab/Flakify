@Test public void should_get_real_object_from_proxy() throws Exception {
  UserBean realObject=new UserBean();
  when(interceptor.getTarget()).thenReturn(realObject);
  Enhancer enhancer=new Enhancer();
  enhancer.setSuperclass(UserBean.class);
  enhancer.setCallback(interceptor);
  UserBean proxy=(UserBean)enhancer.create();
  UserBean actual=proxifier.getRealObject(proxy);
  assertThat(actual).isSameAs(realObject);
}
