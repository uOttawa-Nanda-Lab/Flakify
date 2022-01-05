@Test public void testBufferedInput() throws Exception {
  final int[] result=new int[1];
  HttpRequestHandler requestHandler=new HttpRequestHandler(){
    public void handle(    HttpRequest request,    HttpResponse response,    HttpContext context) throws HttpException, IOException {
      result[0]++;
synchronized (result) {
        result.notify();
      }
    }
  }
;
  NHttpServiceHandler serviceHandler=createHttpServiceHandler(requestHandler,null,null);
  this.server.start(serviceHandler);
  ClassLoader cl=getClass().getClassLoader();
  URL url=cl.getResource("test.keystore");
  KeyStore keystore=KeyStore.getInstance("jks");
  keystore.load(url.openStream(),"nopassword".toCharArray());
  TrustManagerFactory tmfactory=createTrustManagerFactory();
  tmfactory.init(keystore);
  TrustManager[] trustmanagers=tmfactory.getTrustManagers();
  SSLContext sslcontext=SSLContext.getInstance("TLS");
  sslcontext.init(null,trustmanagers,null);
  ListenerEndpoint endpoint=this.server.getListenerEndpoint();
  endpoint.waitFor();
  InetSocketAddress serverAddress=(InetSocketAddress)endpoint.getAddress();
  Socket socket=sslcontext.getSocketFactory().createSocket("localhost",serverAddress.getPort());
  BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
  writer.write("GET / HTTP/1.1\r\n");
  writer.write("Header:                   \r\n");
  writer.write("Header:                   \r\n");
  writer.write("Header:                   \r\n");
  writer.write("\r\n");
  writer.flush();
synchronized (result) {
    result.wait(500);
  }
  Assert.assertEquals(1,result[0]);
}
