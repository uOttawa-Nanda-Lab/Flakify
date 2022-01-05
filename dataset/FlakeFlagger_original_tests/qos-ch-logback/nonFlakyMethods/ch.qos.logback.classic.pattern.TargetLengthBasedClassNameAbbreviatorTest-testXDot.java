@Test public void testXDot(){
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(21);
    String name="com.logback.wombat.alligator.Foobar";
    assertEquals("c.l.w.a.Foobar",abbreviator.abbreviate(name));
  }
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(22);
    String name="com.logback.wombat.alligator.Foobar";
    assertEquals("c.l.w.alligator.Foobar",abbreviator.abbreviate(name));
  }
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(1);
    String name="com.logback.wombat.alligator.tomato.Foobar";
    assertEquals("c.l.w.a.t.Foobar",abbreviator.abbreviate(name));
  }
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(21);
    String name="com.logback.wombat.alligator.tomato.Foobar";
    assertEquals("c.l.w.a.tomato.Foobar",abbreviator.abbreviate(name));
  }
{
    TargetLengthBasedClassNameAbbreviator abbreviator=new TargetLengthBasedClassNameAbbreviator(29);
    String name="com.logback.wombat.alligator.tomato.Foobar";
    assertEquals("c.l.w.alligator.tomato.Foobar",abbreviator.abbreviate(name));
  }
}
