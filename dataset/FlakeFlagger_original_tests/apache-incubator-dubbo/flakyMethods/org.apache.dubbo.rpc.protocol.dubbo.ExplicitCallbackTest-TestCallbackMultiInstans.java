@Test public void TestCallbackMultiInstans() throws Exception {
  initOrResetUrl(2,1000);
  initOrResetService();
  IDemoCallback callback=new IDemoCallback(){
    public String yyy(    String msg){
      System.out.println("callback1:" + msg);
      return "callback1 onChanged ," + msg;
    }
  }
;
  IDemoCallback callback2=new IDemoCallback(){
    public String yyy(    String msg){
      System.out.println("callback2:" + msg);
      return "callback2 onChanged ," + msg;
    }
  }
;
{
    demoProxy.xxx2(callback);
    Assert.assertEquals(1,demoProxy.getCallbackCount());
    Thread.sleep(500);
    demoProxy.unxxx2(callback);
    Assert.assertEquals(0,demoProxy.getCallbackCount());
    System.out.println("");
    demoProxy.xxx2(callback2);
    Assert.assertEquals(1,demoProxy.getCallbackCount());
    Thread.sleep(500);
    demoProxy.unxxx2(callback2);
    Assert.assertEquals(0,demoProxy.getCallbackCount());
    System.out.println("");
    demoProxy.xxx2(callback);
    Thread.sleep(500);
    Assert.assertEquals(1,demoProxy.getCallbackCount());
    demoProxy.unxxx2(callback);
    Assert.assertEquals(0,demoProxy.getCallbackCount());
  }
{
    demoProxy.xxx2(callback);
    Assert.assertEquals(1,demoProxy.getCallbackCount());
    demoProxy.xxx2(callback);
    Assert.assertEquals(1,demoProxy.getCallbackCount());
    demoProxy.xxx2(callback2);
    Assert.assertEquals(2,demoProxy.getCallbackCount());
  }
  destroyService();
}
