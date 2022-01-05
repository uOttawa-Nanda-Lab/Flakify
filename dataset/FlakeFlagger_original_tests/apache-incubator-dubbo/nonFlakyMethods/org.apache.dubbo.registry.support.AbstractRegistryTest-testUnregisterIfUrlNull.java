@Test(expected=IllegalArgumentException.class) public void testUnregisterIfUrlNull() throws Exception {
  abstractRegistry.unregister(null);
  Assert.fail("unregister url == null");
}
