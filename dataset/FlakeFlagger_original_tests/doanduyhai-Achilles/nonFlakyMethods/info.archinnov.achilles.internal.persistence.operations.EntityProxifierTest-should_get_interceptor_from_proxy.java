@Test public void should_get_interceptor_from_proxy() throws Exception {
  Enhancer enhancer=new Enhancer();
  enhancer.setSuperclass(CompleteBean.class);
  enhancer.setCallback(interceptor);
  CompleteBean proxy=(CompleteBean)enhancer.create();
  EntityInterceptor<CompleteBean> actual=proxifier.getInterceptor(proxy);
  assertThat(actual).isSameAs(interceptor);
}
