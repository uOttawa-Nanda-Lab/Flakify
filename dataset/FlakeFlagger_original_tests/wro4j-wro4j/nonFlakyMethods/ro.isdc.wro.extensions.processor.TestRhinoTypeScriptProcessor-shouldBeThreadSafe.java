@Test public void shouldBeThreadSafe() throws Exception {
  final ResourcePreProcessor processor=new RhinoTypeScriptProcessor(){
    @Override protected void onException(    final Exception e,    final String content){
      throw WroRuntimeException.wrap(e);
    }
  }
;
  final Callable<Void> task=new Callable<Void>(){
    @Override public Void call(){
      try {
        processor.process(null,new StringReader("class Animal {}"),new StringWriter());
      }
 catch (      final Exception e) {
        throw new RuntimeException(e);
      }
      return null;
    }
  }
;
  WroTestUtils.runConcurrently(task,7);
}
