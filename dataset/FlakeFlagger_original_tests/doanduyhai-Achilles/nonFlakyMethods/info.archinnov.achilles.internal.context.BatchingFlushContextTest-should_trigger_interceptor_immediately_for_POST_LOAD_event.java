@Test public void should_trigger_interceptor_immediately_for_POST_LOAD_event() throws Exception {
  EntityMeta meta=mock(EntityMeta.class);
  Object entity=new Object();
  context.triggerInterceptor(meta,entity,Event.POST_LOAD);
  verify(meta).intercept(entity,Event.POST_LOAD);
}
