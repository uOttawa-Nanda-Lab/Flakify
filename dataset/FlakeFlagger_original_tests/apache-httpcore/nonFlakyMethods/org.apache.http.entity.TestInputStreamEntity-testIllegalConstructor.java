@Test public void testIllegalConstructor() throws Exception {
  try {
    new InputStreamEntity(null,0);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
