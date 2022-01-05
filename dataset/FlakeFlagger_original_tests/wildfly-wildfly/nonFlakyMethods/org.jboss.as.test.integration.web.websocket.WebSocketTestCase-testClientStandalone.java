@Test @OperateOnDeployment(CLIENT_STANDALONE) @RunAsClient public void testClientStandalone(@ArquillianResource URL webapp) throws Exception {
  assertWebSocket(webapp);
}
