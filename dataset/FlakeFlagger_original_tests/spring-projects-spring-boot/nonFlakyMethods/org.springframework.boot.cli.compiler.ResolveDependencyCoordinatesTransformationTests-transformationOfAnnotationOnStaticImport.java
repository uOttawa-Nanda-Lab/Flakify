@Test public void transformationOfAnnotationOnStaticImport(){
  this.moduleNode.addStaticImport(null,null,null,Arrays.asList(this.grabAnnotation));
  assertGrabAnnotationHasBeenTransformation();
}
