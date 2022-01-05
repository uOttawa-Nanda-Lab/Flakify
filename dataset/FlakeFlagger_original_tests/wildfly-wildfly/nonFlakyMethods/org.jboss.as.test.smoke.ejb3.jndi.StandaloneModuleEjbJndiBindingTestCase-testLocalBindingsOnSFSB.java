/** 
 * Tests that all possible local view bindings of a Stateful bean are available.
 * @throws Exception
 */
@Test public void testLocalBindingsOnSFSB() throws Exception {
  Context ctx=new InitialContext();
  String ejbName=SampleSFSB.class.getSimpleName();
  Echo localBusinessInterface=(Echo)ctx.lookup(JAVA_GLOBAL_NAMESPACE_PREFIX + MODULE_NAME + "/"+ ejbName+ "!"+ Echo.class.getName());
  Assert.assertNotNull("Null object returned for local business interface lookup in java:global namespace",localBusinessInterface);
  SampleSFSB noInterfaceView=(SampleSFSB)ctx.lookup(JAVA_GLOBAL_NAMESPACE_PREFIX + MODULE_NAME + "/"+ ejbName+ "!"+ SampleSFSB.class.getName());
  Assert.assertNotNull("Null object returned for no-interface view lookup in java:global namespace",noInterfaceView);
  Echo localBusinessInterfaceInAppNamespace=(Echo)ctx.lookup(JAVA_APP_NAMESPACE_PREFIX + MODULE_NAME + "/"+ ejbName+ "!"+ Echo.class.getName());
  Assert.assertNotNull("Null object returned for local business interface lookup in java:app namespace",localBusinessInterfaceInAppNamespace);
  SampleSFSB noInterfaceViewInAppNamespace=(SampleSFSB)ctx.lookup(JAVA_APP_NAMESPACE_PREFIX + MODULE_NAME + "/"+ ejbName+ "!"+ SampleSFSB.class.getName());
  Assert.assertNotNull("Null object returned for no-interface view lookup in java:app namespace",noInterfaceViewInAppNamespace);
  Echo localBusinessInterfaceInModuleNamespace=(Echo)ctx.lookup(JAVA_MODULE_NAMESPACE_PREFIX + ejbName + "!"+ Echo.class.getName());
  Assert.assertNotNull("Null object returned for local business interface lookup in java:module namespace",localBusinessInterfaceInModuleNamespace);
  SampleSFSB noInterfaceViewInModuleNamespace=(SampleSFSB)ctx.lookup(JAVA_MODULE_NAMESPACE_PREFIX + ejbName + "!"+ SampleSFSB.class.getName());
  Assert.assertNotNull("Null object returned for no-interface view lookup in java:module namespace",noInterfaceViewInModuleNamespace);
}
