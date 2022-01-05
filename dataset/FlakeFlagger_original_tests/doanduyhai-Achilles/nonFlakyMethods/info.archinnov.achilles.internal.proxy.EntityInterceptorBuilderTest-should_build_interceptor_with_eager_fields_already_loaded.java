@Test public void should_build_interceptor_with_eager_fields_already_loaded() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,String.class).field("id").build();
  EntityMeta meta=new EntityMeta();
  meta.setIdMeta(idMeta);
  meta.setClassName("classname");
  meta.setGetterMetas(new HashMap<Method,PropertyMeta>());
  meta.setSetterMetas(new HashMap<Method,PropertyMeta>());
  when(context.<CompleteBean>getEntityClass()).thenReturn(CompleteBean.class);
  when(context.getEntityMeta()).thenReturn(meta);
  when(context.getPrimaryKey()).thenReturn(entity.getId());
  EntityInterceptor<CompleteBean> interceptor=EntityInterceptorBuilder.<CompleteBean>builder(context,entity).alreadyLoaded(Sets.newHashSet(idMeta.getGetter())).build();
  assertThat(interceptor.getEntityOperations()).isSameAs(context);
  assertThat(interceptor.getTarget()).isSameAs(entity);
  assertThat(interceptor.getPrimaryKey()).isEqualTo(entity.getId());
  assertThat(interceptor.getAlreadyLoaded()).containsOnly(idMeta.getGetter());
}
