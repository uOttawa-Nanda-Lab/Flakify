@Test public void propertyB() throws Exception {
  AnnotationConfigApplicationContext context=load(Config.class,"b:b");
  assertThat(context.containsBean("myBean"),equalTo(true));
  context.close();
}
