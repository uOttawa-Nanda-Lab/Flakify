@Test public void testAcceptProvidedExtensionAsClient() throws Exception {
  DefaultExtension defaultExtension=new DefaultExtension();
  assertTrue(defaultExtension.acceptProvidedExtensionAsClient("Test"));
  assertTrue(defaultExtension.acceptProvidedExtensionAsClient(""));
  assertTrue(defaultExtension.acceptProvidedExtensionAsClient("Test, ASDC, as, ad"));
  assertTrue(defaultExtension.acceptProvidedExtensionAsClient("ASDC, as,ad"));
  assertTrue(defaultExtension.acceptProvidedExtensionAsClient("permessage-deflate"));
}
