@Test public void contentType() throws Exception {
  server.enqueue(new MockResponse().setBody("<html><body><h1>Hello, World!</h1></body></html>").setHeader("Content-Type","text/html"));
  server.enqueue(new MockResponse().setBody("{\"Message\": { \"text\": \"Hello, World!\" } }").setHeader("Content-Type","application/json"));
  server.enqueue(new MockResponse().setBody("Hello, World!"));
  HttpGet request1=new HttpGet(server.getUrl("/").toURI());
  HttpResponse response1=client.execute(request1);
  Header[] headers1=response1.getHeaders("Content-Type");
  assertEquals(1,headers1.length);
  assertEquals("text/html",headers1[0].getValue());
  assertNotNull(response1.getEntity().getContentType());
  assertEquals("text/html",response1.getEntity().getContentType().getValue());
  HttpGet request2=new HttpGet(server.getUrl("/").toURI());
  HttpResponse response2=client.execute(request2);
  Header[] headers2=response2.getHeaders("Content-Type");
  assertEquals(1,headers2.length);
  assertEquals("application/json",headers2[0].getValue());
  assertNotNull(response2.getEntity().getContentType());
  assertEquals("application/json",response2.getEntity().getContentType().getValue());
  HttpGet request3=new HttpGet(server.getUrl("/").toURI());
  HttpResponse response3=client.execute(request3);
  Header[] headers3=response3.getHeaders("Content-Type");
  assertEquals(0,headers3.length);
  assertNull(response3.getEntity().getContentType());
}
