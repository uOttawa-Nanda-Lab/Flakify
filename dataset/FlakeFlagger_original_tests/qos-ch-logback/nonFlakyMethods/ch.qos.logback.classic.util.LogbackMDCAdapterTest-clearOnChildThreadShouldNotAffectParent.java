@Test public void clearOnChildThreadShouldNotAffectParent() throws InterruptedException {
  String firstKey="x" + diff;
  String secondKey="o" + diff;
  mdcAdapter.put(firstKey,firstKey + A_SUFFIX);
  assertEquals(firstKey + A_SUFFIX,mdcAdapter.get(firstKey));
  Thread clearer=new ChildThread(mdcAdapter,firstKey,secondKey){
    @Override public void run(){
      mdcAdapter.clear();
      assertNull(mdcAdapter.get(firstKey));
    }
  }
;
  clearer.start();
  clearer.join();
  assertEquals(firstKey + A_SUFFIX,mdcAdapter.get(firstKey));
}
