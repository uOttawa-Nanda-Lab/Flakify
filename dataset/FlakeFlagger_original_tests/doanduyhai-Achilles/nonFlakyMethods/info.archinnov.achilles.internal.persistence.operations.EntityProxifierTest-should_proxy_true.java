@Test public void should_proxy_true() throws Exception {
  Enhancer enhancer=new Enhancer();
  enhancer.setSuperclass(CompleteBean.class);
  enhancer.setCallback(NoOp.INSTANCE);
  CompleteBean proxy=(CompleteBean)enhancer.create();
  assertThat(proxifier.isProxy(proxy)).isTrue();
}
