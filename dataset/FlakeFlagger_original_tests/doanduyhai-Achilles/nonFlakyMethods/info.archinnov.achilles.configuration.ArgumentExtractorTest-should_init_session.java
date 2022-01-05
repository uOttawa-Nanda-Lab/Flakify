@Test public void should_init_session() throws Exception {
  ConfigMap params=new ConfigMap();
  params.put(KEYSPACE_NAME,"achilles");
  when(cluster.connect("achilles")).thenReturn(session);
  Session actual=extractor.initSession(cluster,params);
  assertThat(actual).isSameAs(session);
}
