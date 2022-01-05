@Test public void testAddDsAndTestConnection() throws Exception {
  final ModelNode address=new ModelNode();
  address.add("subsystem","datasources");
  address.add("data-source","MyNewDs");
  address.protect();
  final ModelNode operation=new ModelNode();
  operation.get(OP).set("add");
  operation.get(OP_ADDR).set(address);
  operation.get("name").set("MyNewDs");
  operation.get("jndi-name").set("java:jboss/datasources/MyNewDs");
  operation.get("enabled").set(true);
  operation.get("driver-name").set("h2");
  operation.get("pool-name").set("MyNewDs_Pool");
  operation.get("connection-url").set("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
  operation.get("user-name").set("sa");
  operation.get("password").set("sa");
  executeOperation(operation);
  testConnection("MyNewDs");
  List<ModelNode> newList=marshalAndReparseDsResources("data-source");
  remove(address);
  Assert.assertNotNull("Reparsing failed:",newList);
  Assert.assertNotNull(findNodeWithProperty(newList,"jndi-name","java:jboss/datasources/MyNewDs"));
}
