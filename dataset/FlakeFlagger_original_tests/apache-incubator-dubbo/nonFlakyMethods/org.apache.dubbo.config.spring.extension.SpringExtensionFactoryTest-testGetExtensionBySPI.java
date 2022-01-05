@Test public void testGetExtensionBySPI(){
  Protocol protocol=springExtensionFactory.getExtension(Protocol.class,"protocol");
  Assert.assertNull(protocol);
}
