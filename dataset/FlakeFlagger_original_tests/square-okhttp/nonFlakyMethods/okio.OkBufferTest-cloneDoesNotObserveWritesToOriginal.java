@Test public void cloneDoesNotObserveWritesToOriginal() throws Exception {
  OkBuffer original=new OkBuffer();
  OkBuffer clone=original.clone();
  original.writeUtf8("abc");
  assertEquals(0,clone.size());
}
