@Test public void testNoDot(){
  TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(1);
  String name="hello";
  assertEquals(name,abbreviator.abbreviate(name));
}
