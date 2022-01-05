@Test public void testConfiguration() throws Throwable {
  assertNotNull("Deployment metadata for ear not found",managementClient.getProtocolMetaData(deploymentName));
  final ModelNode address=new ModelNode();
  address.add("deployment",deploymentName).add("subdeployment",subdeploymentName).add("subsystem","resource-adapters");
  address.protect();
  final ModelNode snapshot=new ModelNode();
  snapshot.get(OP).set("read-resource");
  snapshot.get("recursive").set(true);
  snapshot.get(OP_ADDR).set(address);
  executeOperation(snapshot);
}
