/** 
 * Tests that all possible local view bindings of a Stateless bean are available, when deployed through a .ear
 * @throws Exception
 */
@Test public void testLocalBindingsOnSLSB() throws Exception {
  Context ctx=new InitialContext();
  String ejbName=SampleSLSB.class.getSimpleName();
  Echo localBusinessInterface=(Echo)ctx.lookup(JAVA_GLOBAL_NAMESPACE_PREFIX + APP_NAME + "/"+ MODULE_NAME+ "/"+ ejbName+ "!"+ Echo.class.getName());
  Assert.assertNotNull("Null object returned for local business interface lookup in java:global namespace",localBusinessInterface);
  SampleSLSB noInterfaceView=(SampleSLSB)ctx.lookup(JAVA_GLOBAL_NAMESPACE_PREFIX + APP_NAME + "/"+ MODULE_NAME+ "/"+ ejbName+ "!"+ SampleSLSB.class.getName());
  Assert.assertNotNull("Null object returned for no-interface view lookup in java:global namespace",noInterfaceView);
  Echo localBusinessInterfaceInAppNamespace=(Echo)ctx.lookup(JAVA_APP_NAMESPACE_PREFIX + MODULE_NAME + "/"+ ejbName+ "!"+ Echo.class.getName());
  Assert.assertNotNull("Null object returned for local business interface lookup in java:app namespace",localBusinessInterfaceInAppNamespace);
  SampleSLSB noInterfaceViewInAppNamespace=(SampleSLSB)ctx.lookup(JAVA_APP_NAMESPACE_PREFIX + MODULE_NAME + "/"+ ejbName+ "!"+ SampleSLSB.class.getName());
  Assert.assertNotNull("Null object returned for no-interface view lookup in java:app namespace",noInterfaceViewInAppNamespace);
  Echo localBusinessInterfaceInModuleNamespace=(Echo)ctx.lookup(JAVA_MODULE_NAMESPACE_PREFIX + ejbName + "!"+ Echo.class.getName());
  Assert.assertNotNull("Null object returned for local business interface lookup in java:module namespace",localBusinessInterfaceInModuleNamespace);
  SampleSLSB noInterfaceViewInModuleNamespace=(SampleSLSB)ctx.lookup(JAVA_MODULE_NAMESPACE_PREFIX + ejbName + "!"+ SampleSLSB.class.getName());
  Assert.assertNotNull("Null object returned for no-interface view lookup in java:module namespace",noInterfaceViewInModuleNamespace);
}
