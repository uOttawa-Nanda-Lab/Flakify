@Test public void testNullEntityToByteArray() throws Exception {
  try {
    EntityUtils.toByteArray(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
