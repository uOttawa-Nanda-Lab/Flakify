@Test public void testInvalidateSessions() throws Exception {
  try (CloseableHttpClient client=HttpClients.createDefault()){
    ModelNode operation=new ModelNode();
    operation.get(ModelDescriptionConstants.OP).set("invalidate-session");
    operation.get(ModelDescriptionConstants.OP_ADDR).set(PathAddress.parseCLIStyleAddress("/deployment=invalidate.war/subsystem=undertow").toModelNode());
    operation.get("session-id").set("fake");
    ModelNode opRes=managementClient.getControllerClient().execute(operation);
    Assert.assertEquals("success",opRes.get(ModelDescriptionConstants.OUTCOME).asString());
    Assert.assertEquals(false,opRes.get(ModelDescriptionConstants.RESULT).asBoolean());
    HttpGet get=new HttpGet("http://" + TestSuiteEnvironment.getServerAddress() + ":8080/invalidate/SessionPersistenceServlet");
    HttpResponse res=client.execute(get);
    String sessionId=null;
    for (    Header cookie : res.getHeaders("Set-Cookie")) {
      if (cookie.getValue().startsWith("JSESSIONID=")) {
        sessionId=cookie.getValue().split("=")[1].split("\\.")[0];
        break;
      }
    }
    Assert.assertNotNull(sessionId);
    String result=EntityUtils.toString(res.getEntity());
    assertEquals("0",result);
    result=runGet(get,client);
    assertEquals("1",result);
    result=runGet(get,client);
    assertEquals("2",result);
    operation.get("session-id").set(sessionId);
    opRes=managementClient.getControllerClient().execute(operation);
    Assert.assertEquals("success",opRes.get(ModelDescriptionConstants.OUTCOME).asString());
    Assert.assertEquals(true,opRes.get(ModelDescriptionConstants.RESULT).asBoolean());
    result=runGet(get,client);
    assertEquals("0",result);
    result=runGet(get,client);
    assertEquals("1",result);
  }
 }
