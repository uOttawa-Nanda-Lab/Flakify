@Test public void test3Dot(){
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(1);
    String name="com.logback.xyz.Foobar";
    assertEquals("c.l.x.Foobar",abbreviator.abbreviate(name));
  }
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(13);
    String name="com.logback.xyz.Foobar";
    assertEquals("c.l.x.Foobar",abbreviator.abbreviate(name));
  }
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(14);
    String name="com.logback.xyz.Foobar";
    assertEquals("c.l.xyz.Foobar",abbreviator.abbreviate(name));
  }
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(15);
    String name="com.logback.alligator.Foobar";
    assertEquals("c.l.a.Foobar",abbreviator.abbreviate(name));
  }
}
