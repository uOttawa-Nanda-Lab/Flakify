@Test public void testReadChildrenResources() throws Exception {
  final ModelNode address=new ModelNode();
  address.add("subsystem","datasources");
  address.protect();
  final ModelNode operation=new ModelNode();
  operation.get(OP).set("read-children-resources");
  operation.get("child-type").set("data-source");
  operation.get(OP_ADDR).set(address);
  final ModelNode result=executeOperation(operation);
  final Map<String,ModelNode> children=getChildren(result);
  Assert.assertFalse(children.isEmpty());
  for (  final Entry<String,ModelNode> child : children.entrySet()) {
    Assert.assertNotNull("Default datasource not found",child.getKey());
    Assert.assertTrue("Default datasource have no connection URL",child.getValue().hasDefined("connection-url"));
    Assert.assertTrue("Default datasource have no JNDI name",child.getValue().hasDefined("jndi-name"));
    Assert.assertTrue("Default datasource have no driver",child.getValue().hasDefined("driver-name"));
  }
}
