@Test public void testScanForAnnotation(){
  AnnotationScanner scanner=new AnnotationScanner();
  Set<Class<?>> classSet=scanner.scan("me.prettyprint.hom.beans",javax.persistence.Entity.class);
  int count=0;
  assertTrue(classSet.contains(MyTestBean.class));
  count++;
  assertTrue(classSet.contains(MyBlueTestBean.class));
  count++;
  assertTrue(classSet.contains(MyRedTestBean.class));
  count++;
  assertTrue(classSet.contains(MyPurpleTestBean.class));
  count++;
  assertTrue(classSet.contains(MyCustomIdBean.class));
  count++;
  assertEquals(count,classSet.size());
}
