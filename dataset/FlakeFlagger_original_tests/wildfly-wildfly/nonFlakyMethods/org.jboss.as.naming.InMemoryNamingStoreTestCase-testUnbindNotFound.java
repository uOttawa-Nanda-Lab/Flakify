@Test public void testUnbindNotFound() throws Exception {
  try {
    nameStore.unbind(new CompositeName("test"));
    fail("Should have thrown and NameNotFoundException");
  }
 catch (  NameNotFoundException expected) {
  }
}
