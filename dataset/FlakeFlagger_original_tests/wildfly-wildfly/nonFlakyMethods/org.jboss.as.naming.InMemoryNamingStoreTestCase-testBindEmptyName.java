@Test public void testBindEmptyName() throws Exception {
  try {
    nameStore.bind(new CompositeName(),new Object(),Object.class);
    fail("Should have thrown and InvalidNameException");
  }
 catch (  InvalidNameException expected) {
  }
  try {
    nameStore.bind(new CompositeName(""),new Object(),Object.class);
    fail("Should have thrown and InvalidNameException");
  }
 catch (  InvalidNameException expected) {
  }
}
