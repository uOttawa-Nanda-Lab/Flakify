@Test public void should_return_empty_event_interceptor_list_when_empty_list_parameter() throws Exception {
  List<Interceptor<?>> interceptors=extractor.initInterceptors(configMap);
  assertThat(interceptors).isEmpty();
}
