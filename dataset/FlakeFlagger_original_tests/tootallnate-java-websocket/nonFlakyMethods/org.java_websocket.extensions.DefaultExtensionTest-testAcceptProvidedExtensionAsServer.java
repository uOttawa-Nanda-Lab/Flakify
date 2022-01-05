@Test public void testAcceptProvidedExtensionAsServer() throws Exception {
  DefaultExtension defaultExtension=new DefaultExtension();
  assertTrue(defaultExtension.acceptProvidedExtensionAsServer("Test"));
  assertTrue(defaultExtension.acceptProvidedExtensionAsServer(""));
  assertTrue(defaultExtension.acceptProvidedExtensionAsServer("Test, ASDC, as, ad"));
  assertTrue(defaultExtension.acceptProvidedExtensionAsServer("ASDC, as,ad"));
  assertTrue(defaultExtension.acceptProvidedExtensionAsServer("permessage-deflate"));
}
