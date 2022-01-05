public void testMockResponseAddHeader(){
  MockResponse response=new MockResponse().clearHeaders().addHeader("Cookie: s=square").addHeader("Cookie","a=android");
  assertEquals(Arrays.asList("Cookie: s=square","Cookie: a=android"),response.getHeaders());
}
