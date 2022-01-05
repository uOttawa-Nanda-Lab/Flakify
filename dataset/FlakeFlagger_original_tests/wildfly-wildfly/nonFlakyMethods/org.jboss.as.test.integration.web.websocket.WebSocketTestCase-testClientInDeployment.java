@Test @OperateOnDeployment(CLIENT_IN_DEPLOYMENT) public void testClientInDeployment(@ArquillianResource URL webapp) throws Exception {
  assertWebSocket(webapp);
}
