@Test public void should_reuse_created_proxy_class_from_cache() throws Exception {
  when(configContext.selectClassLoader(CompleteBean.class)).thenReturn(CompleteBean.class.getClassLoader());
  Class<?> proxyClass1=factory.createProxyClass(CompleteBean.class,configContext);
  Class<?> proxyClass2=factory.createProxyClass(CompleteBean.class,configContext);
  assertThat(proxyClass1 == proxyClass2).isTrue();
}
