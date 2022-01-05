@Test public void testSessionManagementOperations() throws Exception {
  try (CloseableHttpClient client=HttpClients.createDefault()){
    ModelNode operation=new ModelNode();
    operation.get(ModelDescriptionConstants.OP).set(LIST_SESSIONS);
    operation.get(ModelDescriptionConstants.OP_ADDR).set(PathAddress.parseCLIStyleAddress("/deployment=management.war/subsystem=undertow").toModelNode());
    ModelNode opRes=managementClient.getControllerClient().execute(operation);
    Assert.assertEquals(opRes.toString(),"success",opRes.get(ModelDescriptionConstants.OUTCOME).asString());
    Assert.assertEquals(Collections.emptyList(),opRes.get(ModelDescriptionConstants.RESULT).asList());
    long c1=System.currentTimeMillis();
    HttpGet get=new HttpGet("http://" + TestSuiteEnvironment.getServerAddress() + ":8080/management/SessionPersistenceServlet");
    HttpResponse res=client.execute(get);
    long c2=System.currentTimeMillis();
    String sessionId=null;
    for (    Header cookie : res.getHeaders("Set-Cookie")) {
      if (cookie.getValue().startsWith("JSESSIONID=")) {
        sessionId=cookie.getValue().split("=")[1].split("\\.")[0];
        break;
      }
    }
    Assert.assertNotNull(sessionId);
    opRes=managementClient.getControllerClient().execute(operation);
    Assert.assertEquals(opRes.toString(),"success",opRes.get(ModelDescriptionConstants.OUTCOME).asString());
    Assert.assertEquals(opRes.toString(),Collections.singletonList(new ModelNode(sessionId)),opRes.get(ModelDescriptionConstants.RESULT).asList());
    operation.get(SESSION_ID).set(sessionId);
    opRes=executeOperation(operation,GET_SESSION_CREATION_TIME_MILLIS);
    long time1=opRes.get(ModelDescriptionConstants.RESULT).asLong();
    Assert.assertTrue(c1 <= time1);
    Assert.assertTrue(time1 <= c2);
    opRes=executeOperation(operation,GET_SESSION_CREATION_TIME);
    long sessionCreationTime=LocalDateTime.parse(opRes.get(ModelDescriptionConstants.RESULT).asString(),DateTimeFormatter.ISO_DATE_TIME).toInstant(ZoneId.systemDefault().getRules().getOffset(Instant.now())).toEpochMilli();
    Assert.assertEquals(time1,sessionCreationTime);
    opRes=executeOperation(operation,GET_SESSION_LAST_ACCESSED_TIME_MILLIS);
    Assert.assertEquals(time1,opRes.get(ModelDescriptionConstants.RESULT).asLong());
    opRes=executeOperation(operation,GET_SESSION_LAST_ACCESSED_TIME);
    long aTime2=LocalDateTime.parse(opRes.get(ModelDescriptionConstants.RESULT).asString(),DateTimeFormatter.ISO_DATE_TIME).toInstant(ZoneId.systemDefault().getRules().getOffset(Instant.now())).toEpochMilli();
    Assert.assertEquals(time1,aTime2);
    Assert.assertEquals(sessionCreationTime,aTime2);
    opRes=executeOperation(operation,LIST_SESSION_ATTRIBUTE_NAMES);
    List<ModelNode> resultList=opRes.get(ModelDescriptionConstants.RESULT).asList();
    Assert.assertEquals(1,resultList.size());
    Assert.assertEquals(opRes.toString(),"val",resultList.get(0).asString());
    opRes=executeOperation(operation,LIST_SESSION_ATTRIBUTES);
    List<Property> properties=opRes.get(ModelDescriptionConstants.RESULT).asPropertyList();
    Assert.assertEquals(opRes.toString(),1,properties.size());
    Property property=properties.get(0);
    Assert.assertEquals(opRes.toString(),"val",property.getName());
    Assert.assertEquals(opRes.toString(),"0",property.getValue().asString());
    Thread.sleep(10);
    long a1=System.currentTimeMillis();
    client.execute(get);
    long a2=System.currentTimeMillis();
    do {
      opRes=executeOperation(operation,GET_SESSION_LAST_ACCESSED_TIME_MILLIS);
      time1=opRes.get(ModelDescriptionConstants.RESULT).asLong();
      if (time1 != sessionCreationTime) {
        break;
      }
    }
 while (System.currentTimeMillis() < a1 + 1000);
    Assert.assertTrue(a1 <= time1);
    Assert.assertTrue(time1 <= a2);
    opRes=executeOperation(operation,GET_SESSION_LAST_ACCESSED_TIME);
    long time2=LocalDateTime.parse(opRes.get(ModelDescriptionConstants.RESULT).asString(),DateTimeFormatter.ISO_DATE_TIME).toInstant(ZoneId.systemDefault().getRules().getOffset(Instant.now())).toEpochMilli();
    Assert.assertEquals(time1,time2);
    operation.get(ATTRIBUTE).set("val");
    opRes=executeOperation(operation,GET_SESSION_ATTRIBUTE);
    Assert.assertEquals("1",opRes.get(ModelDescriptionConstants.RESULT).asString());
    executeOperation(operation,INVALIDATE_SESSION);
    opRes=executeOperation(operation,LIST_SESSIONS);
    Assert.assertEquals(Collections.emptyList(),opRes.get(ModelDescriptionConstants.RESULT).asList());
  }
 }
