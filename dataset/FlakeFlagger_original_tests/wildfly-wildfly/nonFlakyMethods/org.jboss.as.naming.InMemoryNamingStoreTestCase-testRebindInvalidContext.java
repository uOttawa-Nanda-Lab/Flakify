@Test public void testRebindInvalidContext() throws Exception {
  try {
    nameStore.rebind(new CompositeName("subcontext/test"),new Object(),Object.class);
    fail("Should have thrown and NameNotFoundException");
  }
 catch (  NameNotFoundException expected) {
  }
}
