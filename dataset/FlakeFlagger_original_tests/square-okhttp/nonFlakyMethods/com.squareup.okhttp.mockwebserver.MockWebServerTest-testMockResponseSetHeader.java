public void testMockResponseSetHeader(){
  MockResponse response=new MockResponse().clearHeaders().addHeader("Cookie: s=square").addHeader("Cookie: a=android").addHeader("Cookies: delicious");
  response.setHeader("cookie","r=robot");
  assertEquals(Arrays.asList("Cookies: delicious","cookie: r=robot"),response.getHeaders());
}
