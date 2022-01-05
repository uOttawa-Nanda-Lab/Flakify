@Test public void transformationOfAnnotationOnMethod(){
  ClassNode classNode=new ClassNode("Test",0,new ClassNode(Object.class));
  this.moduleNode.addClass(classNode);
  MethodNode methodNode=new MethodNode("test",0,new ClassNode(Void.class),new Parameter[0],new ClassNode[0],null);
  methodNode.addAnnotation(this.grabAnnotation);
  classNode.addMethod(methodNode);
  assertGrabAnnotationHasBeenTransformation();
}
