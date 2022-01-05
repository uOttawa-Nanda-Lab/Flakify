@Test public void jStackDumpTest() throws InterruptedException {
  URL url=URL.valueOf("dubbo://admin:hello1234@10.20.130.230:20880/context/path?dump.directory=/tmp&version=1.0.0&application=morgan&noValue");
  AbortPolicyWithReport abortPolicyWithReport=new AbortPolicyWithReport("Test",url);
  try {
    abortPolicyWithReport.rejectedExecution(new Runnable(){
      @Override public void run(){
        System.out.println("hello");
      }
    }
,(ThreadPoolExecutor)Executors.newFixedThreadPool(1));
  }
 catch (  RejectedExecutionException rj) {
  }
  Thread.sleep(1000);
}
