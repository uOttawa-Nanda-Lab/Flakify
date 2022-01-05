@Test public void testReplaceProperty() throws Exception {
  String s=ConfigUtils.replaceProperty("1${a.b.c}2${a.b.c}3",Collections.singletonMap("a.b.c","ABC"));
  assertEquals(s,"1ABC2ABC3");
  s=ConfigUtils.replaceProperty("1${a.b.c}2${a.b.c}3",Collections.<String,String>emptyMap());
  assertEquals(s,"123");
}
