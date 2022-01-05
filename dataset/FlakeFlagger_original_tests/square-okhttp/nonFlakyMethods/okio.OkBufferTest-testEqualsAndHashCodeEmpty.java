@Test public void testEqualsAndHashCodeEmpty() throws Exception {
  OkBuffer a=new OkBuffer();
  OkBuffer b=new OkBuffer();
  assertTrue(a.equals(b));
  assertTrue(a.hashCode() == b.hashCode());
}
