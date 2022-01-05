@Test public void should_derive_base_class() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().id(1L).buid();
  Enhancer enhancer=new Enhancer();
  enhancer.setSuperclass(entity.getClass());
  enhancer.setCallback(interceptor);
  when(interceptor.getTarget()).thenReturn(entity);
  CompleteBean proxy=(CompleteBean)enhancer.create();
  assertThat(proxifier.<CompleteBean>deriveBaseClass(proxy)).isEqualTo(CompleteBean.class);
}
