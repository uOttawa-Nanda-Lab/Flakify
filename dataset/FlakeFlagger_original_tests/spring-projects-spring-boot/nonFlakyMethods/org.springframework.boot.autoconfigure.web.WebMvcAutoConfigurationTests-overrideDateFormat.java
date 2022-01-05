@Test public void overrideDateFormat() throws Exception {
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.mvc.dateFormat:dd*MM*yyyy");
  this.context.register(AllResources.class,Config.class,WebMvcAutoConfiguration.class,HttpMessageConvertersAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  FormattingConversionService cs=this.context.getBean(FormattingConversionService.class);
  Date date=new DateTime(1988,6,25,20,30).toDate();
  assertThat(cs.convert(date,String.class),equalTo("25*06*1988"));
}
