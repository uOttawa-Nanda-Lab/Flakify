@Test public void transformationOfAnnotationOnStaticStarImport(){
  this.moduleNode.addStaticStarImport(null,null,Arrays.asList(this.grabAnnotation));
  assertGrabAnnotationHasBeenTransformation();
}
