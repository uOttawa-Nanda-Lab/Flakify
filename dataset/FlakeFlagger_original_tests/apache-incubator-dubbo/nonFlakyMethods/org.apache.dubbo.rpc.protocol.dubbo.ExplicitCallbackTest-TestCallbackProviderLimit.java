@Test(expected=RpcException.class) public void TestCallbackProviderLimit() throws Exception {
  initOrResetUrl(1,1000);
  serviceURL=serviceURL.addParameter(Constants.CALLBACK_INSTANCES_LIMIT_KEY,1 + "");
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
  demoProxy.xxx(new IDemoCallback(){
    public String yyy(    String msg){
      System.out.println("Recived callback: " + msg);
      count.incrementAndGet();
      return "ok";
    }
  }
,"other custom args",10,100);
  destroyService();
}
