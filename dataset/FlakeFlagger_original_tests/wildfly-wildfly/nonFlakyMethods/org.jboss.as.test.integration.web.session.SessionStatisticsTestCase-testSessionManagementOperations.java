@Test public void testSessionManagementOperations() throws Exception {
  CloseableHttpClient[] clients=new CloseableHttpClient[CLIENT_COUNT];
  try {
    for (int i=0; i < CLIENT_COUNT; ++i) {
      clients[i]=HttpClients.createDefault();
    }
    ModelNode operation=new ModelNode();
    operation.get(ModelDescriptionConstants.OP).set(ModelDescriptionConstants.READ_RESOURCE_OPERATION);
    operation.get(ModelDescriptionConstants.INCLUDE_RUNTIME).set(true);
    operation.get(ModelDescriptionConstants.OP_ADDR).set(PathAddress.parseCLIStyleAddress("/deployment=stats.war/subsystem=undertow").toModelNode());
    ModelNode opRes=managementClient.getControllerClient().execute(operation);
    Assert.assertEquals(opRes.toString(),"success",opRes.get(ModelDescriptionConstants.OUTCOME).asString());
    ModelNode result=opRes.get(ModelDescriptionConstants.RESULT);
    Assert.assertEquals(0,result.get(DeploymentDefinition.SessionStat.ACTIVE_SESSIONS.toString()).asInt());
    Assert.assertEquals(0,result.get(DeploymentDefinition.SessionStat.EXPIRED_SESSIONS.toString()).asInt());
    Assert.assertEquals(0,result.get(DeploymentDefinition.SessionStat.HIGHEST_SESSION_COUNT.toString()).asInt());
    Assert.assertEquals(4,result.get(DeploymentDefinition.SessionStat.MAX_ACTIVE_SESSIONS.toString()).asInt());
    Assert.assertEquals(0,result.get(DeploymentDefinition.SessionStat.REJECTED_SESSIONS.toString()).asInt());
    Assert.assertEquals(0,result.get(DeploymentDefinition.SessionStat.SESSION_AVG_ALIVE_TIME.toString()).asInt());
    Assert.assertEquals(0,result.get(DeploymentDefinition.SessionStat.SESSION_MAX_ALIVE_TIME.toString()).asInt());
    Assert.assertEquals(0,result.get(DeploymentDefinition.SessionStat.SESSIONS_CREATED.toString()).asInt());
    final HttpGet get=new HttpGet(uri.toString() + "/SessionPersistenceServlet");
    final HttpGet invalidate=new HttpGet(get.getURI().toString() + "?invalidate=true");
    HttpResponse res=clients[0].execute(get);
    Assert.assertEquals(StatusCodes.OK,res.getStatusLine().getStatusCode());
    EntityUtils.consume(res.getEntity());
    opRes=managementClient.getControllerClient().execute(operation);
    Assert.assertEquals(opRes.toString(),"success",opRes.get(ModelDescriptionConstants.OUTCOME).asString());
    result=opRes.get(ModelDescriptionConstants.RESULT);
    Assert.assertEquals(1,result.get(DeploymentDefinition.SessionStat.ACTIVE_SESSIONS.toString()).asInt());
    Assert.assertEquals(0,result.get(DeploymentDefinition.SessionStat.EXPIRED_SESSIONS.toString()).asInt());
    Assert.assertEquals(1,result.get(DeploymentDefinition.SessionStat.HIGHEST_SESSION_COUNT.toString()).asInt());
    Assert.assertEquals(MAX_SESSIONS,result.get(DeploymentDefinition.SessionStat.MAX_ACTIVE_SESSIONS.toString()).asInt());
    Assert.assertEquals(0,result.get(DeploymentDefinition.SessionStat.REJECTED_SESSIONS.toString()).asInt());
    Assert.assertEquals(1,result.get(DeploymentDefinition.SessionStat.SESSIONS_CREATED.toString()).asInt());
    res=clients[0].execute(get);
    Assert.assertEquals(StatusCodes.OK,res.getStatusLine().getStatusCode());
    EntityUtils.consume(res.getEntity());
    opRes=managementClient.getControllerClient().execute(operation);
    Assert.assertEquals(opRes.toString(),"success",opRes.get(ModelDescriptionConstants.OUTCOME).asString());
    result=opRes.get(ModelDescriptionConstants.RESULT);
    Assert.assertEquals(1,result.get(DeploymentDefinition.SessionStat.ACTIVE_SESSIONS.toString()).asInt());
    Assert.assertEquals(0,result.get(DeploymentDefinition.SessionStat.EXPIRED_SESSIONS.toString()).asInt());
    Assert.assertEquals(1,result.get(DeploymentDefinition.SessionStat.HIGHEST_SESSION_COUNT.toString()).asInt());
    Assert.assertEquals(MAX_SESSIONS,result.get(DeploymentDefinition.SessionStat.MAX_ACTIVE_SESSIONS.toString()).asInt());
    Assert.assertEquals(0,result.get(DeploymentDefinition.SessionStat.REJECTED_SESSIONS.toString()).asInt());
    Assert.assertEquals(1,result.get(DeploymentDefinition.SessionStat.SESSIONS_CREATED.toString()).asInt());
    res=clients[0].execute(invalidate);
    Assert.assertEquals(StatusCodes.OK,res.getStatusLine().getStatusCode());
    EntityUtils.consume(res.getEntity());
    opRes=managementClient.getControllerClient().execute(operation);
    Assert.assertEquals(opRes.toString(),"success",opRes.get(ModelDescriptionConstants.OUTCOME).asString());
    result=opRes.get(ModelDescriptionConstants.RESULT);
    Assert.assertEquals(0,result.get(DeploymentDefinition.SessionStat.ACTIVE_SESSIONS.toString()).asInt());
    Assert.assertEquals(1,result.get(DeploymentDefinition.SessionStat.EXPIRED_SESSIONS.toString()).asInt());
    Assert.assertEquals(1,result.get(DeploymentDefinition.SessionStat.HIGHEST_SESSION_COUNT.toString()).asInt());
    Assert.assertEquals(MAX_SESSIONS,result.get(DeploymentDefinition.SessionStat.MAX_ACTIVE_SESSIONS.toString()).asInt());
    Assert.assertEquals(0,result.get(DeploymentDefinition.SessionStat.REJECTED_SESSIONS.toString()).asInt());
    Assert.assertEquals(1,result.get(DeploymentDefinition.SessionStat.SESSIONS_CREATED.toString()).asInt());
    for (int i=0; i < CLIENT_COUNT; ++i) {
      res=clients[i].execute(get);
      Assert.assertEquals(i >= MAX_SESSIONS ? StatusCodes.INTERNAL_SERVER_ERROR : StatusCodes.OK,res.getStatusLine().getStatusCode());
      EntityUtils.consume(res.getEntity());
    }
    opRes=managementClient.getControllerClient().execute(operation);
    Assert.assertEquals(opRes.toString(),"success",opRes.get(ModelDescriptionConstants.OUTCOME).asString());
    result=opRes.get(ModelDescriptionConstants.RESULT);
    Assert.assertEquals(MAX_SESSIONS,result.get(DeploymentDefinition.SessionStat.ACTIVE_SESSIONS.toString()).asInt());
    Assert.assertEquals(1,result.get(DeploymentDefinition.SessionStat.EXPIRED_SESSIONS.toString()).asInt());
    Assert.assertEquals(MAX_SESSIONS,result.get(DeploymentDefinition.SessionStat.HIGHEST_SESSION_COUNT.toString()).asInt());
    Assert.assertEquals(MAX_SESSIONS,result.get(DeploymentDefinition.SessionStat.MAX_ACTIVE_SESSIONS.toString()).asInt());
    Assert.assertEquals(CLIENT_COUNT - MAX_SESSIONS,result.get(DeploymentDefinition.SessionStat.REJECTED_SESSIONS.toString()).asInt());
    Assert.assertEquals(MAX_SESSIONS + 1,result.get(DeploymentDefinition.SessionStat.SESSIONS_CREATED.toString()).asInt());
    for (int i=0; i < MAX_SESSIONS; ++i) {
      res=clients[i].execute(invalidate);
      Assert.assertEquals(StatusCodes.OK,res.getStatusLine().getStatusCode());
      EntityUtils.consume(res.getEntity());
    }
    opRes=managementClient.getControllerClient().execute(operation);
    Assert.assertEquals(opRes.toString(),"success",opRes.get(ModelDescriptionConstants.OUTCOME).asString());
    result=opRes.get(ModelDescriptionConstants.RESULT);
    Assert.assertEquals(0,result.get(DeploymentDefinition.SessionStat.ACTIVE_SESSIONS.toString()).asInt());
    Assert.assertEquals(MAX_SESSIONS + 1,result.get(DeploymentDefinition.SessionStat.EXPIRED_SESSIONS.toString()).asInt());
    Assert.assertEquals(MAX_SESSIONS,result.get(DeploymentDefinition.SessionStat.HIGHEST_SESSION_COUNT.toString()).asInt());
    Assert.assertEquals(MAX_SESSIONS,result.get(DeploymentDefinition.SessionStat.MAX_ACTIVE_SESSIONS.toString()).asInt());
    Assert.assertEquals(CLIENT_COUNT - MAX_SESSIONS,result.get(DeploymentDefinition.SessionStat.REJECTED_SESSIONS.toString()).asInt());
    Assert.assertEquals(MAX_SESSIONS + 1,result.get(DeploymentDefinition.SessionStat.SESSIONS_CREATED.toString()).asInt());
  }
  finally {
    for (    CloseableHttpClient i : clients) {
      IoUtils.safeClose(i);
    }
  }
}
