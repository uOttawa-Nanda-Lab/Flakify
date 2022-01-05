@Test public void cloneDoesNotObserveReadsFromOriginal() throws Exception {
  OkBuffer original=new OkBuffer();
  original.writeUtf8("abc");
  OkBuffer clone=original.clone();
  assertEquals("abc",original.readUtf8(3));
  assertEquals(3,clone.size());
  assertEquals("ab",clone.readUtf8(2));
}
