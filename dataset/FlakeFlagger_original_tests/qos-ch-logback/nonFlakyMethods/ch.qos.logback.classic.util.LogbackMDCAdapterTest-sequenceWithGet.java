@Test public void sequenceWithGet(){
  mdcAdapter.put("k0","v0");
  Map<String,String> map0=mdcAdapter.copyOnInheritThreadLocal.get();
  mdcAdapter.get("k0");
  mdcAdapter.put("k0","v1");
  assertEquals("v0",map0.get("k0"));
}
