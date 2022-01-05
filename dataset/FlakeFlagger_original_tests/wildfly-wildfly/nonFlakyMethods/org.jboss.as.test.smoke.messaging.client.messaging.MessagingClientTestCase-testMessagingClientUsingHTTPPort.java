@Test public void testMessagingClientUsingHTTPPort() throws Exception {
  final ClientSessionFactory sf=createClientSessionFactory(managementClient.getWebUri().getHost(),managementClient.getWebUri().getPort(),true);
  doMessagingClient(sf);
  sf.close();
}
