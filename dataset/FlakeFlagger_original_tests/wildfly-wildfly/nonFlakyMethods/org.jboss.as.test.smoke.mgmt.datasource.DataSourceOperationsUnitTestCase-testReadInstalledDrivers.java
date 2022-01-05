@Test public void testReadInstalledDrivers() throws Exception {
  final ModelNode address=new ModelNode();
  address.add("subsystem","datasources");
  address.protect();
  final ModelNode operation=new ModelNode();
  operation.get(OP).set("installed-drivers-list");
  operation.get(OP_ADDR).set(address);
  final ModelNode result=executeOperation(operation);
  final ModelNode result2=result.get(0);
  Assert.assertNotNull("There are no installed JDBC drivers",result2);
  Assert.assertTrue("Name of JDBC driver is udefined",result2.hasDefined("driver-name"));
  if (!result2.hasDefined("deployment-name")) {
    Assert.assertTrue("Module name of JDBC driver is udefined",result2.hasDefined("driver-module-name"));
    Assert.assertTrue("Module slot of JDBC driver is udefined",result2.hasDefined("module-slot"));
  }
}
