@Test public void both() throws Exception {
  AnnotationConfigApplicationContext context=load(Config.class,"a:a","b:b");
  assertThat(context.containsBean("myBean"),equalTo(true));
  context.close();
}
