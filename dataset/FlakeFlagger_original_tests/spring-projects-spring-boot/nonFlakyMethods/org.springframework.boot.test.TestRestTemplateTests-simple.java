@Test public void simple(){
  assertTrue(new TestRestTemplate().getRequestFactory() instanceof HttpComponentsClientHttpRequestFactory);
}
