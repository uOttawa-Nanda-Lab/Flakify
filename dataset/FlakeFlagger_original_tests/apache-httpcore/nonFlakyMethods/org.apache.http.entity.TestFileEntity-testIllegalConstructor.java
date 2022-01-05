@Test public void testIllegalConstructor() throws Exception {
  try {
    new FileEntity(null,ContentType.create("text/plain",HTTP.ISO_8859_1));
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
