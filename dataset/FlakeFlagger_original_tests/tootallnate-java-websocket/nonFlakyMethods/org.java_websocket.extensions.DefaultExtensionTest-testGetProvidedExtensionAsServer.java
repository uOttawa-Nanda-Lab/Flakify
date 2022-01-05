@Test public void testGetProvidedExtensionAsServer() throws Exception {
  DefaultExtension defaultExtension=new DefaultExtension();
  assertEquals("",defaultExtension.getProvidedExtensionAsServer());
}
