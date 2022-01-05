@Test public void testGetTransportEmpty(){
  try {
    ExtensionLoader.getExtensionLoader(Transporter.class).getExtension("");
    fail();
  }
 catch (  IllegalArgumentException expected) {
    assertThat(expected.getMessage(),containsString("Extension name == null"));
  }
}
