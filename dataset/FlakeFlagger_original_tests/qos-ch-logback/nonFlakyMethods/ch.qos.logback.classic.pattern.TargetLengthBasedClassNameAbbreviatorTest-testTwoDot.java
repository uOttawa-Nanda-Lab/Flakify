@Test public void testTwoDot(){
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(1);
    String name="com.logback.Foobar";
    assertEquals("c.l.Foobar",abbreviator.abbreviate(name));
  }
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(1);
    String name="c.logback.Foobar";
    assertEquals("c.l.Foobar",abbreviator.abbreviate(name));
  }
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(1);
    String name="c..Foobar";
    assertEquals("c..Foobar",abbreviator.abbreviate(name));
  }
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(1);
    String name="..Foobar";
    assertEquals("..Foobar",abbreviator.abbreviate(name));
  }
}
