@Test public void testAutoRemove() throws Exception {
  nameStore.bind(new CompositeName("test/item"),new Object());
  assertNotNull(nameStore.lookup(new CompositeName("test/item")));
  assertNotNull(nameStore.lookup(new CompositeName("test")));
  nameStore.unbind(new CompositeName("test/item"));
  try {
    nameStore.lookup(new CompositeName("test"));
    fail("Should have throw name not found exception");
  }
 catch (  NameNotFoundException expected) {
  }
}
