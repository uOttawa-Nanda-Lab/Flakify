@Test public void transformationOfAnnotationOnConstructor(){
  ClassNode classNode=new ClassNode("Test",0,new ClassNode(Object.class));
  this.moduleNode.addClass(classNode);
  ConstructorNode constructorNode=new ConstructorNode(0,null);
  constructorNode.addAnnotation(this.grabAnnotation);
  classNode.addMethod(constructorNode);
  assertGrabAnnotationHasBeenTransformation();
}
