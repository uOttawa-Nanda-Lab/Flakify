@Test @Config(interfaceClass=Greeting.class,filter={"f1, f2"},listener={"l1, l2"},parameters={"k1","v1","k2","v2"}) public void appendAnnotation() throws Exception {
  Config config=getClass().getMethod("appendAnnotation").getAnnotation(Config.class);
  AnnotationConfig annotationConfig=new AnnotationConfig();
  annotationConfig.appendAnnotation(Config.class,config);
  TestCase.assertSame(Greeting.class,annotationConfig.getInterface());
  TestCase.assertEquals("f1, f2",annotationConfig.getFilter());
  TestCase.assertEquals("l1, l2",annotationConfig.getListener());
  TestCase.assertEquals(2,annotationConfig.getParameters().size());
  TestCase.assertEquals("v1",annotationConfig.getParameters().get("k1"));
  TestCase.assertEquals("v2",annotationConfig.getParameters().get("k2"));
  assertThat(annotationConfig.toString(),Matchers.containsString("filter=\"f1, f2\" "));
  assertThat(annotationConfig.toString(),Matchers.containsString("listener=\"l1, l2\" "));
}
