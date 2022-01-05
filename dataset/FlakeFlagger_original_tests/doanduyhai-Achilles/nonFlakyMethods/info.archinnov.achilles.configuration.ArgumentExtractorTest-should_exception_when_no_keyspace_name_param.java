@Test public void should_exception_when_no_keyspace_name_param() throws Exception {
  ConfigMap params=new ConfigMap();
  exception.expect(AchillesException.class);
  exception.expectMessage(KEYSPACE_NAME + " property should be provided");
  extractor.initSession(cluster,params);
}
