@Test public void smoke(){
  Exception exception=new Exception("smoke");
  exception.printStackTrace(printWriter);
  ILoggingEvent le=createLoggingEvent(exception);
  String result=converter.convert(le);
  result=result.replace("common frames omitted","more");
  result=result.replaceAll(" ~?\\[.*\\]","");
  assertThat(result).isEqualTo(stringWriter.toString());
}
