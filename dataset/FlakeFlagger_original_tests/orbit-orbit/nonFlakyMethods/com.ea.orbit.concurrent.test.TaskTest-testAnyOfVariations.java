@Test public void testAnyOfVariations(){
  CTask<Integer> t1=new CTask();
  CTask t2=new CTask();
  CTask t3=new CTask();
  CompletableFuture c4=new CompletableFuture();
  Task group_regular=CTask.anyOf(t1,t2,t3);
  Task group_array=CTask.anyOf(new CompletableFuture[]{t1,t2,t3});
  Task group_array2=CTask.anyOf(new CTask[]{t1,t2,t3});
  Task group_collection=CTask.anyOf(Arrays.asList(t1,t2,t3));
  Task group_stream=CTask.anyOf(Arrays.asList(t1,t2,t3).stream());
  Stream<CTask> stream=Arrays.asList(t1,t2,t3).stream();
  Task group_stream2=CTask.anyOf(stream);
  Task group_stream3=CTask.anyOf(Arrays.asList(c4).stream());
  Stream<CompletableFuture> stream4=Arrays.asList(t1,t2,t3,c4).stream();
  Task group_stream4=CTask.anyOf(stream4);
  Task group_stream5=CTask.anyOf(Arrays.asList(t1,t2,t3,c4).stream());
  t1.complete(1);
  c4.complete(4);
  assertTrue(group_regular.isDone());
  assertTrue(group_array.isDone());
  assertTrue(group_array2.isDone());
  assertTrue(group_stream.isDone());
  assertTrue(group_stream2.isDone());
  assertTrue(group_stream3.isDone());
  assertTrue(group_stream4.isDone());
  assertTrue(group_stream5.isDone());
  assertTrue(group_collection.isDone());
}
