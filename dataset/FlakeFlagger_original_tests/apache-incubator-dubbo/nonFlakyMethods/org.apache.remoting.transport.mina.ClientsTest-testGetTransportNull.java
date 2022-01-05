@Test(expected=IllegalArgumentException.class) public void testGetTransportNull(){
  String name=null;
  ExtensionLoader.getExtensionLoader(Transporter.class).getExtension(name);
}
