@Test public void updateConcurrent() throws Exception {
  final SimpleInMemoryRepository<Integer> repository=new SimpleInMemoryRepository<Integer>();
  Collection<Callable<Boolean>> tasks=new ArrayList<Callable<Boolean>>();
  for (int i=0; i < 1000; i++) {
    tasks.add(new Callable<Boolean>(){
      @Override public Boolean call() throws Exception {
        repository.update("foo",new Callback<Integer>(){
          @Override public Integer modify(          Integer current){
            if (current == null) {
              return 1;
            }
            return current + 1;
          }
        }
);
        return true;
      }
    }
);
    tasks.add(new Callable<Boolean>(){
      @Override public Boolean call() throws Exception {
        repository.update("foo",new Callback<Integer>(){
          @Override public Integer modify(          Integer current){
            if (current == null) {
              return -1;
            }
            return current - 1;
          }
        }
);
        return true;
      }
    }
);
  }
  List<Future<Boolean>> all=Executors.newFixedThreadPool(10).invokeAll(tasks);
  for (  Future<Boolean> future : all) {
    assertTrue(future.get(1,TimeUnit.SECONDS));
  }
  assertEquals(new Integer(0),repository.findOne("foo"));
}
