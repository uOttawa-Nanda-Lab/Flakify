/** 
 * AS7-1203 test for missing xa-datasource properties
 * @throws Exception
 */
@Test public void testAddXaDsWithProperties() throws Exception {
  final String xaDs="MyNewXaDs";
  final String xaDsJndi="java:jboss/xa-datasources/" + xaDs;
  final ModelNode address=new ModelNode();
  address.add("subsystem","datasources");
  address.add("xa-data-source",xaDs);
  address.protect();
  final ModelNode operation=new ModelNode();
  operation.get(OP).set("add");
  operation.get(OP_ADDR).set(address);
  operation.get("name").set(xaDs);
  operation.get("jndi-name").set(xaDsJndi);
  operation.get("enabled").set(false);
  operation.get("driver-name").set("h2");
  operation.get("xa-datasource-class").set("org.jboss.as.connector.subsystems.datasources.ModifiableXaDataSource");
  operation.get("pool-name").set(xaDs + "_Pool");
  operation.get("user-name").set("sa");
  operation.get("password").set("sa");
  executeOperation(operation);
  final ModelNode xaDatasourcePropertiesAddress=address.clone();
  xaDatasourcePropertiesAddress.add("xa-datasource-properties","URL");
  xaDatasourcePropertiesAddress.protect();
  final ModelNode xaDatasourcePropertyOperation=new ModelNode();
  xaDatasourcePropertyOperation.get(OP).set("add");
  xaDatasourcePropertyOperation.get(OP_ADDR).set(xaDatasourcePropertiesAddress);
  xaDatasourcePropertyOperation.get("value").set("jdbc:h2:mem:test");
  executeOperation(xaDatasourcePropertyOperation);
  final ModelNode operation2=new ModelNode();
  operation2.get(OP).set("write-attribute");
  operation2.get("name").set("enabled");
  operation2.get("value").set(true);
  operation2.get(OP_ADDR).set(address);
  executeOperation(operation2);
  List<ModelNode> newList=marshalAndReparseDsResources("xa-data-source");
  remove(address);
  Assert.assertNotNull("Reparsing failed:",newList);
  Assert.assertNotNull(findNodeWithProperty(newList,"jndi-name",xaDsJndi));
}
