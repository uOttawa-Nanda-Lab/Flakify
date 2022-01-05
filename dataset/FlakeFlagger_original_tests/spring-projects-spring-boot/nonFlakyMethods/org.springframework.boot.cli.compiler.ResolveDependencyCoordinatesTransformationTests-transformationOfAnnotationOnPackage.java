@Test public void transformationOfAnnotationOnPackage(){
  PackageNode packageNode=new PackageNode("test");
  packageNode.addAnnotation(this.grabAnnotation);
  this.moduleNode.setPackage(packageNode);
  assertGrabAnnotationHasBeenTransformation();
}
