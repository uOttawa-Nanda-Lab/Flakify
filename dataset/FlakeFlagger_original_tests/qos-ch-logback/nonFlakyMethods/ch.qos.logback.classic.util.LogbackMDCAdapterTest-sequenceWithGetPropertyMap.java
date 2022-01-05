@Test public void sequenceWithGetPropertyMap(){
  mdcAdapter.put("k0","v0");
  Map<String,String> map0=mdcAdapter.getPropertyMap();
  mdcAdapter.put("k0","v1");
  assertEquals("v0",map0.get("k0"));
}
