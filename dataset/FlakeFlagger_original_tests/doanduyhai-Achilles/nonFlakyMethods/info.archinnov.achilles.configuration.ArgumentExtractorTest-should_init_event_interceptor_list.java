@Test public void should_init_event_interceptor_list() throws Exception {
  ImmutableList<Interceptor<?>> interceptorsExcepted=new ImmutableList.Builder<Interceptor<?>>().add(interceptor1).add(interceptor2).build();
  configMap.put(EVENT_INTERCEPTORS,interceptorsExcepted);
  doCallRealMethod().when(extractor).initInterceptors(configMap);
  List<Interceptor<?>> interceptorsResult=extractor.initInterceptors(configMap);
  assertThat(interceptorsResult).containsExactly(interceptor1,interceptor2);
}
