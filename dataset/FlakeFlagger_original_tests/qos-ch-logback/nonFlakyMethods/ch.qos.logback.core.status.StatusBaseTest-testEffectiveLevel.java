public void testEffectiveLevel(){
{
    ErrorStatus status=new ErrorStatus("error",this);
    WarnStatus warn=new WarnStatus("warning",this);
    status.add(warn);
    assertEquals("effective level misevaluated",status.getEffectiveLevel(),Status.ERROR);
  }
{
    InfoStatus status=new InfoStatus("info",this);
    WarnStatus warn=new WarnStatus("warning",this);
    status.add(warn);
    assertEquals("effective level misevaluated",status.getEffectiveLevel(),Status.WARN);
  }
{
    InfoStatus status=new InfoStatus("info",this);
    WarnStatus warn=new WarnStatus("warning",this);
    ErrorStatus error=new ErrorStatus("error",this);
    status.add(warn);
    warn.add(error);
    assertEquals("effective level misevaluated",status.getEffectiveLevel(),Status.ERROR);
  }
}
