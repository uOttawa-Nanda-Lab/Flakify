@Test public void originalDoesNotObserveWritesToClone() throws Exception {
  OkBuffer original=new OkBuffer();
  OkBuffer clone=original.clone();
  clone.writeUtf8("abc");
  assertEquals(0,original.size());
}
