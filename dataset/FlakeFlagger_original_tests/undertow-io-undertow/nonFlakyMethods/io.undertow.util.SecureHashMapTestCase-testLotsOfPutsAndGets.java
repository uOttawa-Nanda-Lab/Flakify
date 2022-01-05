@Test public void testLotsOfPutsAndGets(){
  SessionIdGenerator generator=new SecureRandomSessionIdGenerator();
  final Map<String,String> reference=new HashMap<String,String>();
  final SecureHashMap<String,String> map=new SecureHashMap<String,String>();
  for (int i=0; i < 10000; ++i) {
    String key=generator.createSessionId();
    String value=generator.createSessionId();
    map.put(key,value);
    reference.put(key,value);
  }
  for (  Map.Entry<String,String> entry : reference.entrySet()) {
    Assert.assertEquals(entry.getValue(),map.get(entry.getKey()));
    Assert.assertEquals(entry.getValue(),map.remove(entry.getKey()));
  }
  Assert.assertEquals(0,map.size());
}
