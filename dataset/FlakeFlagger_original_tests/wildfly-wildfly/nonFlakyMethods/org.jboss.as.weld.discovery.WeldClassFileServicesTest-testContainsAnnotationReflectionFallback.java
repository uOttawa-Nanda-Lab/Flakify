@Test public void testContainsAnnotationReflectionFallback(){
  Assert.assertTrue(charlie.containsAnnotation(Target.class));
  Assert.assertTrue(bravo.containsAnnotation(Target.class));
}
