/** 
 * Tests that all possible remote view bindings of a Stateful bean are available.
 * @throws Exception
 */
@Test public void testRemoteBindingsOnSFSB() throws Exception {
  Context ctx=new InitialContext();
  String ejbName=SampleSFSB.class.getSimpleName();
  RemoteEcho remoteBusinessInterface=(RemoteEcho)ctx.lookup(JAVA_GLOBAL_NAMESPACE_PREFIX + MODULE_NAME + "/"+ ejbName+ "!"+ RemoteEcho.class.getName());
  Assert.assertNotNull("Null object returned for remote business interface lookup in java:global namespace",remoteBusinessInterface);
  RemoteEcho remoteBusinessInterfaceInAppNamespace=(RemoteEcho)ctx.lookup(JAVA_APP_NAMESPACE_PREFIX + MODULE_NAME + "/"+ ejbName+ "!"+ RemoteEcho.class.getName());
  Assert.assertNotNull("Null object returned for remote business interface lookup in java:app namespace",remoteBusinessInterfaceInAppNamespace);
  RemoteEcho remoteBusinessInterfaceInModuleNamespace=(RemoteEcho)ctx.lookup(JAVA_MODULE_NAMESPACE_PREFIX + ejbName + "!"+ RemoteEcho.class.getName());
  Assert.assertNotNull("Null object returned for remote business interface lookup in java:module namespace",remoteBusinessInterfaceInModuleNamespace);
}
