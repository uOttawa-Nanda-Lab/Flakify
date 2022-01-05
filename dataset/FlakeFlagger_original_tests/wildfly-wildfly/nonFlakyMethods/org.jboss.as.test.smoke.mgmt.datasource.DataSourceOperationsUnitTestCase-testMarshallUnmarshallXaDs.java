/** 
 * AS7-1200 test case for xa datasource persistence to xml
 * @throws Exception
 */
@Test public void testMarshallUnmarshallXaDs() throws Exception {
  final String dsName="XaDsName2";
  final String jndiDsName="XaJndiDsName2";
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
  marshalAndReparseDsResources("xa-data-source");
  Assert.assertNotNull(findNodeWithProperty(newList,"jndi-name","java:jboss/datasources/" + jndiDsName));
}
