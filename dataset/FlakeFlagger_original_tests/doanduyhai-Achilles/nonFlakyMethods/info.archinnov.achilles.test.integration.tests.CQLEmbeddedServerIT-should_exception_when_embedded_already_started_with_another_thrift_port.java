@Test public void should_exception_when_embedded_already_started_with_another_thrift_port() throws Exception {
  String cassandraHost=System.getProperty(CassandraEmbeddedServer.CASSANDRA_HOST);
  if (StringUtils.isBlank(cassandraHost)) {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("An embedded Cassandra server is already listening to Thrift port");
  }
  CassandraEmbeddedServerBuilder.noEntityPackages().withKeyspaceName("test_keyspace").withThriftPort(9500).buildNativeSessionOnly();
}
