@Test public void transformationOfAnnotationOnStarImport(){
  this.moduleNode.addStarImport("org.springframework.util",Arrays.asList(this.grabAnnotation));
  assertGrabAnnotationHasBeenTransformation();
}
