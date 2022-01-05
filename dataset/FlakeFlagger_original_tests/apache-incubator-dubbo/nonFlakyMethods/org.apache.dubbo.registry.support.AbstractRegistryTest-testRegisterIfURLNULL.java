@Test(expected=IllegalArgumentException.class) public void testRegisterIfURLNULL() throws Exception {
  abstractRegistry.register(null);
  Assert.fail("register url == null");
}
