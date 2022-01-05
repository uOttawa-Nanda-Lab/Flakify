@Test public void headers() throws Exception {
  server.enqueue(new MockResponse().addHeader("Foo","Bar"));
  server.enqueue(new MockResponse().addHeader("Foo","Bar").addHeader("Foo","Baz"));
  HttpGet request1=new HttpGet(server.getUrl("/").toURI());
  HttpResponse response1=client.execute(request1);
  Header[] headers1=response1.getHeaders("Foo");
  assertEquals(1,headers1.length);
  assertEquals("Bar",headers1[0].getValue());
  HttpGet request2=new HttpGet(server.getUrl("/").toURI());
  HttpResponse response2=client.execute(request2);
  Header[] headers2=response2.getHeaders("Foo");
  assertEquals(2,headers2.length);
  assertEquals("Bar",headers2[0].getValue());
  assertEquals("Baz",headers2[1].getValue());
}
