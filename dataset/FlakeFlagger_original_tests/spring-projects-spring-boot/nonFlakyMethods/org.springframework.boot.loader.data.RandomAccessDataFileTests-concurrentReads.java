@Test public void concurrentReads() throws Exception {
  ExecutorService executorService=Executors.newFixedThreadPool(20);
  List<Future<Boolean>> results=new ArrayList<Future<Boolean>>();
  for (int i=0; i < 100; i++) {
    results.add(executorService.submit(new Callable<Boolean>(){
      @Override public Boolean call() throws Exception {
        InputStream subsectionInputStream=RandomAccessDataFileTests.this.file.getSubsection(0,256).getInputStream(ResourceAccess.PER_READ);
        byte[] b=new byte[256];
        subsectionInputStream.read(b);
        return Arrays.equals(b,BYTES);
      }
    }
));
  }
  for (  Future<Boolean> future : results) {
    assertThat(future.get(),equalTo(true));
  }
}
