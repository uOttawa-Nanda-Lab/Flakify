@Test public void testMessagingClientUsingMessagingPort() throws Exception {
  final ClientSessionFactory sf=createClientSessionFactory(managementClient.getWebUri().getHost(),messagingPort,false);
  doMessagingClient(sf);
  sf.close();
}
