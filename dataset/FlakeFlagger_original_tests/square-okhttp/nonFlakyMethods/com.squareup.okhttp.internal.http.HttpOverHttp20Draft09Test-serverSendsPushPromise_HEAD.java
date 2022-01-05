@Test public void serverSendsPushPromise_HEAD() throws Exception {
  MockResponse response=new MockResponse().setBody("ABCDE").setStatus("HTTP/1.1 200 Sweet").withPush(new PushPromise("HEAD","/foo/bar",Arrays.asList("foo: bar"),new MockResponse().setStatus("HTTP/1.1 204 Sweet")));
  server.enqueue(response);
  server.play();
  connection=client.open(server.getUrl("/foo"));
  assertContent("ABCDE",connection,Integer.MAX_VALUE);
  assertEquals(200,connection.getResponseCode());
  assertEquals("Sweet",connection.getResponseMessage());
  RecordedRequest request=server.takeRequest();
  assertEquals("GET /foo HTTP/1.1",request.getRequestLine());
  assertContains(request.getHeaders(),":scheme: https");
  assertContains(request.getHeaders(),hostHeader + ": " + hostName+ ":"+ server.getPort());
  RecordedRequest pushedRequest=server.takeRequest();
  assertEquals("HEAD /foo/bar HTTP/1.1",pushedRequest.getRequestLine());
  assertEquals(Arrays.asList("foo: bar"),pushedRequest.getHeaders());
}
