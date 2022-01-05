public void testRegularResponse() throws Exception {
  server.enqueue(new MockResponse().setBody("hello world"));
  server.play();
  URL url=server.getUrl("/");
  HttpURLConnection connection=(HttpURLConnection)url.openConnection();
  connection.setRequestProperty("Accept-Language","en-US");
  InputStream in=connection.getInputStream();
  BufferedReader reader=new BufferedReader(new InputStreamReader(in));
  assertEquals(HttpURLConnection.HTTP_OK,connection.getResponseCode());
  assertEquals("hello world",reader.readLine());
  RecordedRequest request=server.takeRequest();
  assertEquals("GET / HTTP/1.1",request.getRequestLine());
  assertTrue(request.getHeaders().contains("Accept-Language: en-US"));
}
