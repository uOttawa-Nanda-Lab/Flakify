@Test public void overrideLocale() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.mvc.locale:en_UK");
  this.context.register(AllResources.class,Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  MockHttpServletRequest request=new MockHttpServletRequest();
  request.addPreferredLocale(StringUtils.parseLocaleString("nl_NL"));
  LocaleResolver localeResolver=this.context.getBean(LocaleResolver.class);
  Locale locale=localeResolver.resolveLocale(request);
  assertThat(localeResolver,instanceOf(FixedLocaleResolver.class));
  assertThat(locale.toString(),equalTo("en_UK"));
}
