@Test public void nearSimultaneousPutsShouldNotCauseConcurrentModificationException() throws InterruptedException {
  for (int i=0; i < 2048; i++) {
    mdcAdapter.put("k" + i,"v" + i);
  }
  ChildThread childThread=new ChildThread(mdcAdapter,null,null){
    @Override public void run(){
      for (int i=0; i < 16; i++) {
        mdcAdapter.put("ck" + i,"cv" + i);
        Thread.yield();
      }
      successful=true;
    }
  }
;
  childThread.start();
  Thread.sleep(1);
  for (int i=0; i < 16; i++) {
    mdcAdapter.put("K" + i,"V" + i);
  }
  childThread.join();
  assertTrue(childThread.successful);
}
