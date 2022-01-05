@Test public void originalDoesNotObserveReadsFromClone() throws Exception {
  OkBuffer original=new OkBuffer();
  original.writeUtf8("abc");
  OkBuffer clone=original.clone();
  assertEquals("abc",clone.readUtf8(3));
  assertEquals(3,original.size());
  assertEquals("ab",original.readUtf8(2));
}
