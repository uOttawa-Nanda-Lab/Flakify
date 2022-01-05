@Test public void should_trigger_interceptor() throws Exception {
  EntityMeta meta=mock(EntityMeta.class);
  Object entity=new Object();
  context.triggerInterceptor(meta,entity,Event.POST_PERSIST);
  verify(meta).intercept(entity,Event.POST_PERSIST);
}
