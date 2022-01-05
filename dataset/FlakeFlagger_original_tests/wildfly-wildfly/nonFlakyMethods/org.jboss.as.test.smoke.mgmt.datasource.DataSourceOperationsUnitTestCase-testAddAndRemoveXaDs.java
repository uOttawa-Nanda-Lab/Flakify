@Test public void testAddAndRemoveXaDs() throws Exception {
  final String dsName="XaDsName";
  final String jndiDsName="XaJndiDsName";
  final ModelNode address=new ModelNode();
  address.add("subsystem","datasources");
  address.add("xa-data-source",dsName);
  address.protect();
  final ModelNode operation=new ModelNode();
  operation.get(OP).set("add");
  operation.get(OP_ADDR).set(address);
  operation.get("name").set(dsName);
  operation.get("jndi-name").set("java:jboss/datasources/" + jndiDsName);
  operation.get("enabled").set(false);
  operation.get("driver-name").set("h2");
  operation.get("pool-name").set(dsName + "_Pool");
  operation.get("user-name").set("sa");
  operation.get("password").set("sa");
  executeOperation(operation);
  final ModelNode xaDatasourcePropertiesAddress=address.clone();
  xaDatasourcePropertiesAddress.add("xa-datasource-properties","URL");
  xaDatasourcePropertiesAddress.protect();
  final ModelNode xaDatasourcePropertyOperation=new ModelNode();
  xaDatasourcePropertyOperation.get(OP).set("add");
  xaDatasourcePropertyOperation.get(OP_ADDR).set(xaDatasourcePropertiesAddress);
  xaDatasourcePropertyOperation.get("value").set("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
  executeOperation(xaDatasourcePropertyOperation);
  remove(address);
}
