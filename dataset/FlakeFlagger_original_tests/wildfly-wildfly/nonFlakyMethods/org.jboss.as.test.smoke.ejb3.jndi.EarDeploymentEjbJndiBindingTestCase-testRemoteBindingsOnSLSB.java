/** 
 * Tests that all possible remote view bindings of a Stateless bean are available, when deployed through a .ear
 * @throws Exception
 */
@Test public void testRemoteBindingsOnSLSB() throws Exception {
  Context ctx=new InitialContext();
  String ejbName=SampleSLSB.class.getSimpleName();
  RemoteEcho remoteBusinessInterface=(RemoteEcho)ctx.lookup(JAVA_GLOBAL_NAMESPACE_PREFIX + APP_NAME + "/"+ MODULE_NAME+ "/"+ ejbName+ "!"+ RemoteEcho.class.getName());
  Assert.assertNotNull("Null object returned for remote business interface lookup in java:global namespace",remoteBusinessInterface);
  RemoteEcho remoteBusinessInterfaceInAppNamespace=(RemoteEcho)ctx.lookup(JAVA_APP_NAMESPACE_PREFIX + MODULE_NAME + "/"+ ejbName+ "!"+ RemoteEcho.class.getName());
  Assert.assertNotNull("Null object returned for remote business interface lookup in java:app namespace",remoteBusinessInterfaceInAppNamespace);
  RemoteEcho remoteBusinessInterfaceInModuleNamespace=(RemoteEcho)ctx.lookup(JAVA_MODULE_NAMESPACE_PREFIX + ejbName + "!"+ RemoteEcho.class.getName());
  Assert.assertNotNull("Null object returned for remote business interface lookup in java:module namespace",remoteBusinessInterfaceInModuleNamespace);
}
