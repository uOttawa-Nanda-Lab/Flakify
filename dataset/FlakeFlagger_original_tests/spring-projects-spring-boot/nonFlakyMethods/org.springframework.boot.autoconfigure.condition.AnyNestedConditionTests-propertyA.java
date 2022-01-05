@Test public void propertyA() throws Exception {
  AnnotationConfigApplicationContext context=load(Config.class,"a:a");
  assertThat(context.containsBean("myBean"),equalTo(true));
  context.close();
}
