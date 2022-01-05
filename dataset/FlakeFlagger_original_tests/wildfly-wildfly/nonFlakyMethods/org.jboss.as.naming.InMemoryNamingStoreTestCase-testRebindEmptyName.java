@Test public void testRebindEmptyName() throws Exception {
  try {
    nameStore.rebind(new CompositeName(),new Object(),Object.class);
    fail("Should have thrown and InvalidNameException");
  }
 catch (  InvalidNameException expected) {
  }
  try {
    nameStore.rebind(new CompositeName(""),new Object(),Object.class);
    fail("Should have thrown and InvalidNameException");
  }
 catch (  InvalidNameException expected) {
  }
}
