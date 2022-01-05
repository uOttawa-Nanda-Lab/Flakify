@Test public void should_add_event_interceptors_to_entity_metas() throws Exception {
  final EntityMeta metaString=new EntityMeta();
  final EntityMeta metaLong=new EntityMeta();
  final List<Interceptor<?>> interceptors=Arrays.<Interceptor<?>>asList(stringInterceptor1,stringInterceptor2,longInterceptor);
  final Map<Class<?>,EntityMeta> entityMetaMap=ImmutableMap.<Class<?>,EntityMeta>of(String.class,metaString,Long.class,metaLong);
  bootstrapper.addInterceptorsToEntityMetas(interceptors,entityMetaMap);
  assertThat(metaString.getInterceptors()).contains(stringInterceptor1,stringInterceptor2);
  assertThat(metaLong.getInterceptors()).contains(longInterceptor);
}
