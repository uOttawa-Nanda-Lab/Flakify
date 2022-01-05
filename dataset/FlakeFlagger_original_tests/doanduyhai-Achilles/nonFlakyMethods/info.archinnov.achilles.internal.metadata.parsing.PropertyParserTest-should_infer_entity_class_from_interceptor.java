@SuppressWarnings({"unchecked","rawtypes"}) @Test public void should_infer_entity_class_from_interceptor() throws Exception {
  assertThat(parser.inferEntityClassFromInterceptor(longInterceptor)).isEqualTo((Class)Long.class);
}
