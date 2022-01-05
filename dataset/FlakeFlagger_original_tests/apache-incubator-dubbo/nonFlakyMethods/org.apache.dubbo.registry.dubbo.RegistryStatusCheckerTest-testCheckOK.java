@Test public void testCheckOK(){
  ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension().getRegistry(registryUrl);
  ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension().getRegistry(registryUrl2);
  assertEquals(Status.Level.OK,new RegistryStatusChecker().check().getLevel());
  String message=new RegistryStatusChecker().check().getMessage();
  Assert.assertTrue(message.contains(registryUrl.getAddress() + "(connected)"));
  Assert.assertTrue(message.contains(registryUrl2.getAddress() + "(connected)"));
}
