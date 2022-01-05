@Test public void testGetProvidedExtensionAsClient() throws Exception {
  DefaultExtension defaultExtension=new DefaultExtension();
  assertEquals("",defaultExtension.getProvidedExtensionAsClient());
}
