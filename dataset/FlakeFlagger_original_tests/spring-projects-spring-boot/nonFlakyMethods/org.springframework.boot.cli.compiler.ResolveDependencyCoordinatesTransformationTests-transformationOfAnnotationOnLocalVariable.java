@Test public void transformationOfAnnotationOnLocalVariable(){
  ClassNode classNode=new ClassNode("Test",0,new ClassNode(Object.class));
  this.moduleNode.addClass(classNode);
  DeclarationExpression declarationExpression=new DeclarationExpression(new VariableExpression("test"),null,new ConstantExpression("test"));
  declarationExpression.addAnnotation(this.grabAnnotation);
  BlockStatement code=new BlockStatement(Arrays.asList((Statement)new ExpressionStatement(declarationExpression)),new VariableScope());
  MethodNode methodNode=new MethodNode("test",0,new ClassNode(Void.class),new Parameter[0],new ClassNode[0],code);
  classNode.addMethod(methodNode);
  assertGrabAnnotationHasBeenTransformation();
}
