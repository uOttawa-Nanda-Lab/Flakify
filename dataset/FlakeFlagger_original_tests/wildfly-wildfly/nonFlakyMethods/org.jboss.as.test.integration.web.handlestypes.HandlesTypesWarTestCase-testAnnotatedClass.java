@Test public void testAnnotatedClass(){
  Class<?>[] expeccted={AnnotatedParent.class,AnnotatedChild.class};
  Assert.assertEquals(new HashSet<>(Arrays.asList(expeccted)),AnnotationServletContainerInitializer.HANDLES_TYPES);
}
