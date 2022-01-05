@Test public void concurrentModification(){
  final int runLen=100;
  Thread thread=new Thread(new Runnable(){
    public void run(){
      for (int i=0; i < runLen; i++) {
        lc.getLogger("a" + i);
        Thread.yield();
      }
    }
  }
);
  thread.start();
  for (int i=0; i < runLen; i++) {
    lc.putProperty("a" + i,"val");
    Thread.yield();
  }
}
