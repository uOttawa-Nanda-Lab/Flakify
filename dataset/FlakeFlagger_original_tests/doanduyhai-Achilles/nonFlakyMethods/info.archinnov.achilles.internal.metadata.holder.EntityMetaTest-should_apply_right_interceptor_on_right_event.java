@Test public void should_apply_right_interceptor_on_right_event() throws Exception {
  CompleteBean bean=CompleteBeanTestBuilder.builder().id(12L).buid();
  EntityMeta entityMeta=new EntityMeta();
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,Long.class).field("id").type(PropertyType.EMBEDDED_ID).accessors().build();
  idMeta.setInvoker(new ReflectionInvoker());
  entityMeta.setIdMeta(idMeta);
  entityMeta.addInterceptor(createInterceptorForCompleteBean(PRE_PERSIST,30L));
  entityMeta.addInterceptor(createInterceptorForCompleteBean(POST_PERSIST,35L));
  entityMeta.intercept(bean,PRE_PERSIST);
  Assertions.assertThat(bean.getAge()).isEqualTo(30L);
  entityMeta.intercept(bean,POST_PERSIST);
  Assertions.assertThat(bean.getAge()).isEqualTo(35L);
}
