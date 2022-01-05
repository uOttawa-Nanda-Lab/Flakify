@Test public void testShortName(){
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(100);
    String name="hello";
    assertEquals(name,abbreviator.abbreviate(name));
  }
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(100);
    String name="hello.world";
    assertEquals(name,abbreviator.abbreviate(name));
  }
}
