@Test public void testReadResourceResources() throws Exception {
  final ModelNode address=new ModelNode();
  address.add("subsystem","datasources");
  address.protect();
  final ModelNode operation=new ModelNode();
  operation.get(OP).set("read-resource-description");
  operation.get(OP_ADDR).set(address);
  final ModelNode result=executeOperation(operation);
  final Map<String,ModelNode> children=getChildren(result.get("attributes").get("installed-drivers").get("value-type"));
  Assert.assertFalse(children.isEmpty());
  HashSet<String> keys=new HashSet<String>();
  for (  final Entry<String,ModelNode> child : children.entrySet()) {
    Assert.assertNotNull("Default driver description have no attributes",child.getKey());
    keys.add(child.getKey());
  }
  Assert.assertTrue("Default driver description have no xa-datasource-class attribute",keys.contains("driver-xa-datasource-class-name"));
  Assert.assertTrue("Default driver description have no module-slot attribute",keys.contains("module-slot"));
  Assert.assertTrue("Default driver description have no driver-name attribute",keys.contains("driver-name"));
}
