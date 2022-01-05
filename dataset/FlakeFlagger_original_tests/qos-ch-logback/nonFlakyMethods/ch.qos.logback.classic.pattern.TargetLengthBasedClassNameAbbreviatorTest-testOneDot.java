@Test public void testOneDot(){
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(1);
    String name="hello.world";
    assertEquals("h.world",abbreviator.abbreviate(name));
  }
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(1);
    String name="h.world";
    assertEquals("h.world",abbreviator.abbreviate(name));
  }
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(1);
    String name=".world";
    assertEquals(".world",abbreviator.abbreviate(name));
  }
}
