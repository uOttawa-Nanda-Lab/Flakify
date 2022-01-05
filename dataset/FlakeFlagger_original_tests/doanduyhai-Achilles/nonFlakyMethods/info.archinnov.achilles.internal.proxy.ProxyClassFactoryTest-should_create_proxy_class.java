@Test public void should_create_proxy_class() throws Exception {
  when(configContext.selectClassLoader(CompleteBean.class)).thenReturn(CompleteBean.class.getClassLoader());
  Class<?> proxyClass=factory.createProxyClass(CompleteBean.class,configContext);
  assertThat(CompleteBean.class.isAssignableFrom(proxyClass)).isTrue();
  assertThat(Factory.class.isAssignableFrom(proxyClass)).isTrue();
}
