@Test public void authenticated(){
  assertTrue(new TestRestTemplate("user","password").getRequestFactory() instanceof InterceptingClientHttpRequestFactory);
}
