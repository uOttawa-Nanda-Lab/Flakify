@Test public void should_get_native_session_from_parameter() throws Exception {
  ConfigMap params=new ConfigMap();
  params.put(KEYSPACE_NAME,"achilles");
  params.put(NATIVE_SESSION,session);
  Session actual=extractor.initSession(cluster,params);
  assertThat(actual).isSameAs(session);
}
