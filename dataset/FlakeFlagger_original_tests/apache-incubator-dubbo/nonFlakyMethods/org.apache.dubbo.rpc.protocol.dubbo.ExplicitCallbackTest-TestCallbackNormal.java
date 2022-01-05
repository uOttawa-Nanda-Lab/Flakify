@Test public void TestCallbackNormal() throws Exception {
  initOrResetUrl(1,10000000);
  initOrResetService();
  final AtomicInteger count=new AtomicInteger(0);
  demoProxy.xxx(new IDemoCallback(){
    public String yyy(    String msg){
      System.out.println("Recived callback: " + msg);
      count.incrementAndGet();
      return "ok";
    }
  }
,"other custom args",10,100);
  System.out.println("Async...");
  assertCallbackCount(10,100,count);
  destroyService();
}
