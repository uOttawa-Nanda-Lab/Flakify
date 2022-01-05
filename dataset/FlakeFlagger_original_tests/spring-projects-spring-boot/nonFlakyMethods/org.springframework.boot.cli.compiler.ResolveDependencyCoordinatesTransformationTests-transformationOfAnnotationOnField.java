@Test public void transformationOfAnnotationOnField(){
  ClassNode classNode=new ClassNode("Test",0,new ClassNode(Object.class));
  this.moduleNode.addClass(classNode);
  FieldNode fieldNode=new FieldNode("test",0,new ClassNode(Object.class),classNode,null);
  classNode.addField(fieldNode);
  fieldNode.addAnnotation(this.grabAnnotation);
  assertGrabAnnotationHasBeenTransformation();
}
