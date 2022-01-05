public void testStringToLong(){
  Context context=new ContextBase();
  SizeBasedTriggeringPolicy policy=new SizeBasedTriggeringPolicy();
  policy.setContext(context);
  Long result;
{
    result=policy.toFileSize("123");
    assertEquals(new Long("123"),result);
  }
{
    result=policy.toFileSize("123KB");
    assertEquals(new Long("125952"),result);
  }
{
    result=policy.toFileSize("123MB");
    assertEquals(new Long("128974848"),result);
  }
{
    result=policy.toFileSize("123GB");
    assertEquals(new Long("132070244352"),result);
  }
{
    result=policy.toFileSize("123xxxx");
    assertEquals(new Long(SizeBasedTriggeringPolicy.DEFAULT_MAX_FILE_SIZE),result);
    assertEquals(2,context.getStatusManager().getCount());
  }
}
