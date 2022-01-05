@Test public void should_return_event_interceptors_for_specific_event() throws Exception {
  EntityMeta entityMeta=new EntityMeta();
  Interceptor<String> postPersistInterceptor=createInterceptor(POST_PERSIST);
  Interceptor<String> prePersistInterceptor=createInterceptor(PRE_PERSIST);
  entityMeta.addInterceptor(postPersistInterceptor);
  entityMeta.addInterceptor(prePersistInterceptor);
  assertThat(entityMeta.getInterceptorsForEvent(POST_PERSIST)).containsExactly(postPersistInterceptor);
  assertThat(entityMeta.getInterceptorsForEvent(PRE_PERSIST)).containsExactly(prePersistInterceptor);
}
