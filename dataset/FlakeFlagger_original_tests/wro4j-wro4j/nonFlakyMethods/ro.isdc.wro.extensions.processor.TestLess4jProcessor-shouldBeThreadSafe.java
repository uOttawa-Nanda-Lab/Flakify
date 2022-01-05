@Test public void shouldBeThreadSafe() throws Exception {
  final ResourcePreProcessor processor=new Less4jProcessor();
  final Callable<Void> task=new Callable<Void>(){
    @Override public Void call(){
      try {
        processor.process(null,new StringReader("#id {.class {color: red;}}"),new StringWriter());
      }
 catch (      final Exception e) {
        throw new RuntimeException(e);
      }
      return null;
    }
  }
;
  WroTestUtils.runConcurrently(task);
}
