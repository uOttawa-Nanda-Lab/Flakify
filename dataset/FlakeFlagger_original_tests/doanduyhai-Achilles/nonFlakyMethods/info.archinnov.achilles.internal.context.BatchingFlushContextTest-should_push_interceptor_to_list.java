@Test public void should_push_interceptor_to_list() throws Exception {
  EntityMeta meta=mock(EntityMeta.class);
  Object entity=new Object();
  context.triggerInterceptor(meta,entity,Event.POST_PERSIST);
  verify(meta,never()).intercept(entity,Event.POST_PERSIST);
  assertThat(context.eventHolders).hasSize(1);
  final EventHolder eventHolder=context.eventHolders.get(0);
  eventHolder.triggerInterception();
  verify(meta).intercept(entity,Event.POST_PERSIST);
}
