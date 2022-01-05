@Test public void transformationOfAnnotationOnClass(){
  ClassNode classNode=new ClassNode("Test",0,new ClassNode(Object.class));
  classNode.addAnnotation(this.grabAnnotation);
  this.moduleNode.addClass(classNode);
  assertGrabAnnotationHasBeenTransformation();
}
