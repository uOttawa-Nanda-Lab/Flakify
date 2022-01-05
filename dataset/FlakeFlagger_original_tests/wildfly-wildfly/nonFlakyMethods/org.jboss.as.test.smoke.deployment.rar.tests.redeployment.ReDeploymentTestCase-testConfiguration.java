/** 
 * Test configuration
 * @throws Throwable Thrown if case of an error
 */
@Test public void testConfiguration() throws Throwable {
  deployer.deploy(deploymentName);
  setup();
  deployer.undeploy(deploymentName);
  deployer.deploy(deploymentName);
  MultipleAdminObject1 adminObject1=(MultipleAdminObject1)context.lookup("redeployed/Name3");
  assertNotNull("AO1 not found",adminObject1);
  deployer.undeploy(deploymentName);
}
