@Test public void testHttpContent() throws Exception {
  String[] patterns={"0123456789ABCDEF","yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-"+ "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-"+ "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-"+ "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-"+ "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-"+ "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-"+ "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-"+ "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-"+ "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-"+ "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-"+ "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-"+ "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-"+ "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that"};
  this.server.registerHandler("*",new HttpRequestHandler(){
    public void handle(    final HttpRequest request,    final HttpResponse response,    final HttpContext context) throws HttpException, IOException {
      if (request instanceof HttpEntityEnclosingRequest) {
        int n=1;
        String s=request.getRequestLine().getUri();
        if (s.startsWith("/?n=")) {
          s=s.substring(4);
          try {
            n=Integer.parseInt(s);
            if (n <= 0) {
              throw new HttpException("Invalid request: " + "number of repetitions cannot be negative or zero");
            }
          }
 catch (          NumberFormatException ex) {
            throw new HttpException("Invalid request: " + "number of repetitions is invalid");
          }
        }
        HttpEntity incoming=((HttpEntityEnclosingRequest)request).getEntity();
        String line=EntityUtils.toString(incoming);
        ContentType contentType=ContentType.getOrDefault(incoming);
        String charset=contentType.getCharset();
        if (charset == null) {
          charset=HTTP.DEFAULT_CONTENT_CHARSET;
        }
        RepeatingEntity outgoing=new RepeatingEntity(line,charset,n);
        outgoing.setChunked(n % 2 == 0);
        response.setEntity(outgoing);
      }
 else {
        throw new HttpException("Invalid request: POST request expected");
      }
    }
  }
);
  this.server.start();
  DefaultHttpClientConnection conn=new DefaultHttpClientConnection();
  HttpHost host=new HttpHost("localhost",this.server.getPort());
  try {
    for (int i=0; i < patterns.length; i++) {
      String pattern=patterns[i];
      for (int n=1000; n < 1020; n++) {
        if (!conn.isOpen()) {
          Socket socket=new Socket(host.getHostName(),host.getPort());
          conn.bind(socket,this.client.getParams());
        }
        BasicHttpEntityEnclosingRequest post=new BasicHttpEntityEnclosingRequest("POST","/?n=" + n);
        StringEntity outgoing=new StringEntity(pattern);
        outgoing.setChunked(n % 2 == 0);
        post.setEntity(outgoing);
        HttpResponse response=this.client.execute(post,host,conn);
        HttpEntity incoming=response.getEntity();
        Assert.assertNotNull(incoming);
        InputStream instream=incoming.getContent();
        ContentType contentType=ContentType.getOrDefault(incoming);
        String charset=contentType.getCharset();
        if (charset == null) {
          charset=HTTP.DEFAULT_CONTENT_CHARSET;
        }
        Assert.assertNotNull(instream);
        BufferedReader reader=new BufferedReader(new InputStreamReader(instream,charset));
        String line;
        int count=0;
        while ((line=reader.readLine()) != null) {
          Assert.assertEquals(pattern,line);
          count++;
        }
        Assert.assertEquals(n,count);
        if (!this.client.keepAlive(response)) {
          conn.close();
        }
      }
    }
  }
  finally {
    conn.close();
    this.server.shutdown();
  }
}
