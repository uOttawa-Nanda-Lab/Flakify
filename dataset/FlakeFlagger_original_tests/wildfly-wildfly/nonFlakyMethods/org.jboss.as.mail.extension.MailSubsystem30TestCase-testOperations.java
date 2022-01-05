@Test public void testOperations() throws Exception {
  KernelServicesBuilder builder=createKernelServicesBuilder(new DefaultInitializer()).setSubsystemXml(getSubsystemXml());
  KernelServices mainServices=builder.build();
  if (!mainServices.isSuccessfulBoot()) {
    Assert.fail(mainServices.getBootError().toString());
  }
  PathAddress sessionAddress=PathAddress.pathAddress(MailExtension.SUBSYSTEM_PATH,PathElement.pathElement(MailExtension.MAIL_SESSION_PATH.getKey(),"defaultMail"));
  ModelNode result;
  ModelNode removeServerOp=Util.createRemoveOperation(sessionAddress.append("server","imap"));
  removeServerOp.get(OPERATION_HEADERS).get(ALLOW_RESOURCE_SERVICE_RESTART).set(true);
  result=mainServices.executeOperation(removeServerOp);
  checkResult(result);
  ModelNode addServerOp=Util.createAddOperation(sessionAddress.append("server","imap"));
  addServerOp.get(OPERATION_HEADERS).get(ALLOW_RESOURCE_SERVICE_RESTART).set(true);
  addServerOp.get("outbound-socket-binding-ref").set("mail-imap");
  addServerOp.get("username").set("user");
  addServerOp.get("password").set("pswd");
  result=mainServices.executeOperation(addServerOp);
  checkResult(result);
  checkResult(mainServices.executeOperation(removeServerOp));
  checkResult(mainServices.executeOperation(addServerOp));
  ModelNode writeOp=Util.createEmptyOperation(WRITE_ATTRIBUTE_OPERATION,sessionAddress);
  writeOp.get(OPERATION_HEADERS).get(ALLOW_RESOURCE_SERVICE_RESTART).set(true);
  writeOp.get("name").set("debug");
  writeOp.get("value").set(false);
  result=mainServices.executeOperation(writeOp);
  checkResult(result);
  ServiceController<?> javaMailService=mainServices.getContainer().getService(MailSessionDefinition.SESSION_CAPABILITY.getCapabilityServiceName("defaultMail"));
  javaMailService.setMode(ServiceController.Mode.ACTIVE);
  Session session=(Session)javaMailService.getValue();
  Assert.assertNotNull("session should not be null",session);
  Properties properties=session.getProperties();
  Assert.assertNotNull("smtp host should be set",properties.getProperty("mail.smtp.host"));
  Assert.assertNotNull("imap host should be set",properties.getProperty("mail.imap.host"));
  PathAddress nonExisting=PathAddress.pathAddress(MailExtension.SUBSYSTEM_PATH,PathElement.pathElement(MailExtension.MAIL_SESSION_PATH.getKey(),"non-existing-session"));
  ModelNode addSession=Util.createAddOperation(nonExisting);
  addSession.get("jndi-name").set("java:/bah");
  checkResult(mainServices.executeOperation(addSession));
  removeServerOp=Util.createRemoveOperation(nonExisting.append("server","imap"));
  result=mainServices.executeOperation(removeServerOp);
  checkForFailure(result);
}
