@Test public void testRegistry(){
  if (PerformanceUtils.getProperty("server",null) == null) {
    logger.warn("Please set -Dserver=127.0.0.1:9090");
    return;
  }
  final int base=PerformanceUtils.getIntProperty("base",0);
  final int concurrent=PerformanceUtils.getIntProperty("concurrent",100);
  int r=PerformanceUtils.getIntProperty("runs",1000);
  final int runs=r > 0 ? r : Integer.MAX_VALUE;
  final Registry registry=ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension().getRegistry(URL.valueOf("remote://admin:hello1234@" + PerformanceUtils.getProperty("server","10.20.153.28:9090")));
  for (int i=0; i < concurrent; i++) {
    final int t=i;
    new Thread(new Runnable(){
      public void run(){
        for (int j=0; j < runs; j++) {
          registry.register(URL.valueOf("remote://" + NetUtils.getLocalHost() + ":8080/demoService"+ t+ "_"+ j+ "?version=1.0.0&application=demo&dubbo=2.0&interface="+ "org.apache.dubbo.demo.DemoService"+ (base + t)+ "_"+ (base + j)));
        }
      }
    }
).start();
  }
synchronized (PerformanceRegistryTest.class) {
    while (true) {
      try {
        PerformanceRegistryTest.class.wait();
      }
 catch (      InterruptedException e) {
      }
    }
  }
}
