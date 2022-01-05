@Test public void transformationOfAnnotationOnImport(){
  this.moduleNode.addImport(null,null,Arrays.asList(this.grabAnnotation));
  assertGrabAnnotationHasBeenTransformation();
}
