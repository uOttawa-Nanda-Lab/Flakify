@Test public void testMinimizeAttributePresence(){
  final WroModel model=loadModelFromLocation("wro-minimizeAttribute.xml");
  final Group group=model.getGroupByName(new WroModelInspector(model).getGroupNames().get(0));
  final List<Resource> resourceList=group.getResources();
  LOG.debug("resources: " + resourceList);
  assertEquals(false,resourceList.get(0).isMinimize());
  assertEquals(true,resourceList.get(1).isMinimize());
  assertEquals(true,resourceList.get(2).isMinimize());
  LOG.debug("model: " + model);
}
