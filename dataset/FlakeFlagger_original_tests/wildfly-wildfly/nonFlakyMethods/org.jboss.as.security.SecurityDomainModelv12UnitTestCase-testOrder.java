@Test public void testOrder() throws Exception {
  KernelServices service=createKernelServicesBuilder(createAdditionalInitialization()).setSubsystemXmlResource("securitysubsystemv12.xml").build();
  PathAddress address=PathAddress.pathAddress().append("subsystem","security").append("security-domain","ordering");
  address=address.append("authentication","classic");
  ModelNode writeOp=Util.createOperation("write-attribute",address);
  writeOp.get("name").set("login-modules");
  for (int i=1; i <= 6; i++) {
    ModelNode module=writeOp.get("value").add();
    module.get("code").set("module-" + i);
    module.get("flag").set("optional");
    module.get("module-options");
  }
  service.executeOperation(writeOp);
  ModelNode readOp=Util.createOperation("read-attribute",address);
  readOp.get("name").set("login-modules");
  ModelNode result=service.executeForResult(readOp);
  List<ModelNode> modules=result.asList();
  Assert.assertEquals("There should be exactly 6 modules but there are not",6,modules.size());
  for (int i=1; i <= 6; i++) {
    ModelNode module=modules.get(i - 1);
    Assert.assertEquals(module.get("code").asString(),"module-" + i);
  }
}
