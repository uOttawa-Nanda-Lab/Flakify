@Test public void testIllegalConstructor() throws Exception {
  try {
    new EntityTemplate(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
