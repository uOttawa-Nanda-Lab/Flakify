@Test public void restartWithKeepAlive() throws Exception {
  AbstractEmbeddedServletContainerFactory factory=getFactory();
  this.container=factory.getEmbeddedServletContainer(exampleServletRegistration());
  this.container.start();
  HttpComponentsAsyncClientHttpRequestFactory clientHttpRequestFactory=new HttpComponentsAsyncClientHttpRequestFactory();
  ListenableFuture<ClientHttpResponse> response1=clientHttpRequestFactory.createAsyncRequest(new URI(getLocalUrl("/hello")),HttpMethod.GET).executeAsync();
  assertThat(response1.get(10,TimeUnit.SECONDS).getRawStatusCode(),equalTo(200));
  this.container.stop();
  this.container=factory.getEmbeddedServletContainer(exampleServletRegistration());
  this.container.start();
  ListenableFuture<ClientHttpResponse> response2=clientHttpRequestFactory.createAsyncRequest(new URI(getLocalUrl("/hello")),HttpMethod.GET).executeAsync();
  assertThat(response2.get(10,TimeUnit.SECONDS).getRawStatusCode(),equalTo(200));
}
