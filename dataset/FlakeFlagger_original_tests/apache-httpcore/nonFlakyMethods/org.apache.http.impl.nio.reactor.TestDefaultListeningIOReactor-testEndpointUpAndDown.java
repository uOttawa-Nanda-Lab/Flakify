@Test public void testEndpointUpAndDown() throws Exception {
  HttpParams params=new SyncBasicHttpParams();
  HttpProcessor httpproc=new ImmutableHttpProcessor(new HttpResponseInterceptor[]{new ResponseDate(),new ResponseServer(),new ResponseContent(),new ResponseConnControl()});
  final BufferingHttpServiceHandler serviceHandler=new BufferingHttpServiceHandler(httpproc,new DefaultHttpResponseFactory(),new DefaultConnectionReuseStrategy(),params);
  final IOEventDispatch eventDispatch=new DefaultServerIOEventDispatch(serviceHandler,params);
  IOReactorConfig config=new IOReactorConfig();
  config.setIoThreadCount(1);
  final ListeningIOReactor ioreactor=new DefaultListeningIOReactor(config);
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
  Set<ListenerEndpoint> endpoints=ioreactor.getEndpoints();
  Assert.assertNotNull(endpoints);
  Assert.assertEquals(0,endpoints.size());
  ListenerEndpoint port9998=ioreactor.listen(new InetSocketAddress(9998));
  port9998.waitFor();
  ListenerEndpoint port9999=ioreactor.listen(new InetSocketAddress(9999));
  port9999.waitFor();
  endpoints=ioreactor.getEndpoints();
  Assert.assertNotNull(endpoints);
  Assert.assertEquals(2,endpoints.size());
  port9998.close();
  endpoints=ioreactor.getEndpoints();
  Assert.assertNotNull(endpoints);
  Assert.assertEquals(1,endpoints.size());
  ListenerEndpoint endpoint=endpoints.iterator().next();
  Assert.assertEquals(9999,((InetSocketAddress)endpoint.getAddress()).getPort());
  ioreactor.shutdown(1000);
  t.join(1000);
  Assert.assertEquals(IOReactorStatus.SHUT_DOWN,ioreactor.getStatus());
}
