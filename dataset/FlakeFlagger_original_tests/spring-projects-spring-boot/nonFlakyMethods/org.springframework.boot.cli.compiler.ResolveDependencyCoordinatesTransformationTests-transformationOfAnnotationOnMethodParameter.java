@Test public void transformationOfAnnotationOnMethodParameter(){
  ClassNode classNode=new ClassNode("Test",0,new ClassNode(Object.class));
  this.moduleNode.addClass(classNode);
  Parameter parameter=new Parameter(new ClassNode(Object.class),"test");
  parameter.addAnnotation(this.grabAnnotation);
  MethodNode methodNode=new MethodNode("test",0,new ClassNode(Void.class),new Parameter[]{parameter},new ClassNode[0],null);
  classNode.addMethod(methodNode);
  assertGrabAnnotationHasBeenTransformation();
}
