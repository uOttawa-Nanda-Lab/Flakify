@Test public void testEndpointAlreadyBoundNonFatal() throws Exception {
  HttpParams params=new SyncBasicHttpParams();
  HttpProcessor httpproc=new ImmutableHttpProcessor(new HttpResponseInterceptor[]{new ResponseDate(),new ResponseServer(),new ResponseContent(),new ResponseConnControl()});
  final BufferingHttpServiceHandler serviceHandler=new BufferingHttpServiceHandler(httpproc,new DefaultHttpResponseFactory(),new DefaultConnectionReuseStrategy(),params);
  final IOEventDispatch eventDispatch=new DefaultServerIOEventDispatch(serviceHandler,params);
  IOReactorConfig config=new IOReactorConfig();
  config.setIoThreadCount(1);
  final DefaultListeningIOReactor ioreactor=new DefaultListeningIOReactor(config);
  ioreactor.setExceptionHandler(new IOReactorExceptionHandler(){
    public boolean handle(    final IOException ex){
      return (ex instanceof BindException);
    }
    public boolean handle(    final RuntimeException ex){
      return false;
    }
  }
);
  Thread t=new Thread(new Runnable(){
    public void run(){
      try {
        ioreactor.execute(eventDispatch);
      }
 catch (      IOException ex) {
      }
    }
  }
);
  t.start();
  ListenerEndpoint endpoint1=ioreactor.listen(new InetSocketAddress(9999));
  endpoint1.waitFor();
  ListenerEndpoint endpoint2=ioreactor.listen(new InetSocketAddress(9999));
  endpoint2.waitFor();
  Assert.assertNotNull(endpoint2.getException());
  Thread.sleep(500);
  Assert.assertEquals(IOReactorStatus.ACTIVE,ioreactor.getStatus());
  ioreactor.shutdown(1000);
  t.join(1000);
  Assert.assertEquals(IOReactorStatus.SHUT_DOWN,ioreactor.getStatus());
}
