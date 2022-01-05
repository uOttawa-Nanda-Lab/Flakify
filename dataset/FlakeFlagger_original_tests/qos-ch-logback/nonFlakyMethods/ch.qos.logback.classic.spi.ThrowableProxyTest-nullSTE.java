@Test public void nullSTE(){
  Throwable t=new Exception("someMethodWithNullException"){
    @Override public StackTraceElement[] getStackTrace(){
      return null;
    }
  }
;
  new ThrowableProxy(t);
  Exception top=new Exception("top",t);
  new ThrowableProxy(top);
}
