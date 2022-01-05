@Test public void noGson() throws Exception {
  this.context.register(HttpMessageConvertersAutoConfiguration.class);
  this.context.refresh();
  assertTrue(this.context.getBeansOfType(Gson.class).isEmpty());
  assertTrue(this.context.getBeansOfType(GsonHttpMessageConverter.class).isEmpty());
}
