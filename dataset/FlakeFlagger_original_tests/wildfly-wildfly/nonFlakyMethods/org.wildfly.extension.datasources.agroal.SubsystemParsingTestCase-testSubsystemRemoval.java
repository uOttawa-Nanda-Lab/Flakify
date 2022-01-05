/** 
 * Tests that the subsystem can be removed
 */
@Test public void testSubsystemRemoval() throws Exception {
  String subsystemXml="<subsystem xmlns=\"" + AgroalNamespace.CURRENT.getUriString() + "\"/>";
  KernelServices services=super.createKernelServicesBuilder(null).setSubsystemXml(subsystemXml).build();
  super.assertRemoveSubsystemResources(services);
}
