@Test public void testSessionManagementOperationsNegative() throws Exception {
  try (CloseableHttpClient client=HttpClients.createDefault()){
    ModelNode operation=new ModelNode();
    operation.get(ModelDescriptionConstants.OP_ADDR).set(PathAddress.parseCLIStyleAddress("/deployment=management.war/subsystem=undertow").toModelNode());
    String sessionId="non-existing-id";
    operation.get(SESSION_ID).set(sessionId);
    negativeTestsCheck(operation,LIST_SESSION_ATTRIBUTE_NAMES);
    negativeTestsCheck(operation,LIST_SESSION_ATTRIBUTES);
    operation.get(ATTRIBUTE).set("val");
    negativeTestsCheck(operation,GET_SESSION_ATTRIBUTE);
    executeOperation(operation,INVALIDATE_SESSION);
    HttpGet get=new HttpGet("http://" + TestSuiteEnvironment.getServerAddress() + ":8080/management/SessionPersistenceServlet");
    HttpResponse res=client.execute(get);
    sessionId=null;
    for (    Header cookie : res.getHeaders("Set-Cookie")) {
      if (cookie.getValue().startsWith("JSESSIONID=")) {
        sessionId=cookie.getValue().split("=")[1].split("\\.")[0];
        break;
      }
    }
    operation.get(SESSION_ID).set(sessionId);
    operation.get(ATTRIBUTE).set("non-existing");
    ModelNode opRes=executeOperation(operation,GET_SESSION_ATTRIBUTE);
    Assert.assertEquals("undefined",opRes.get(ModelDescriptionConstants.RESULT).asString());
    executeOperation(operation,INVALIDATE_SESSION);
  }
 }
