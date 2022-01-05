@Test public void testReadWriteEmptySubsystem() throws Exception {
  String subsystemXml="<subsystem xmlns=\"" + Namespace.CURRENT.getUriString() + "\">"+ "</subsystem>";
  ModelNode testModel=new ModelNode();
  testModel.get(ModelDescriptionConstants.SUBSYSTEM,ConfigAdminExtension.SUBSYSTEM_NAME).setEmptyObject();
  String triggered=outputModel(testModel);
  Assert.assertEquals(normalizeXML(subsystemXml),normalizeXML(triggered));
}
