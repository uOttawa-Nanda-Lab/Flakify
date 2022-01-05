@Test public void testAddAndRemoveSameName() throws Exception {
  final String dsName="SameNameDs";
  final ModelNode address=new ModelNode();
  address.add("subsystem","datasources");
  address.add("data-source",dsName);
  address.protect();
  final ModelNode operation=new ModelNode();
  operation.get(OP).set("add");
  operation.get(OP_ADDR).set(address);
  operation.get("name").set(dsName);
  operation.get("jndi-name").set("java:jboss/datasources/" + dsName);
  operation.get("enabled").set(false);
  operation.get("driver-name").set("h2");
  operation.get("pool-name").set(dsName + "_Pool");
  operation.get("connection-url").set("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
  operation.get("user-name").set("sa");
  operation.get("password").set("sa");
  for (int i=1; i <= 2; i++) {
    executeOperation(operation);
    remove(address);
  }
}
