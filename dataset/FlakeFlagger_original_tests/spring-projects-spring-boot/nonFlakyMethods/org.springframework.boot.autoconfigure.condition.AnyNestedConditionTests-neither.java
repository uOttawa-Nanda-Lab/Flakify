@Test public void neither() throws Exception {
  AnnotationConfigApplicationContext context=load(OnPropertyAorBCondition.class);
  assertThat(context.containsBean("myBean"),equalTo(false));
  context.close();
}
