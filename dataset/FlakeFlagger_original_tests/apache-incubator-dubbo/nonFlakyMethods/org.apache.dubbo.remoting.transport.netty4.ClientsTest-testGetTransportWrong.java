@Test(expected=IllegalStateException.class) public void testGetTransportWrong(){
  String name="nety";
  assertNull(ExtensionLoader.getExtensionLoader(Transporter.class).getExtension(name).getClass());
}
