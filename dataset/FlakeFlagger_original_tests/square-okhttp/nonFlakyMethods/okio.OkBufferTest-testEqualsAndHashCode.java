@Test public void testEqualsAndHashCode() throws Exception {
  OkBuffer a=new OkBuffer().writeUtf8("dog");
  OkBuffer b=new OkBuffer().writeUtf8("hotdog");
  assertFalse(a.equals(b));
  assertFalse(a.hashCode() == b.hashCode());
  b.readUtf8(3);
  assertTrue(a.equals(b));
  assertTrue(a.hashCode() == b.hashCode());
}
