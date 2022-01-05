@Test public void assertPersistShardingInfoTransactionExecutionCallback() throws Exception {
  CuratorTransactionFinal curatorTransactionFinal=mock(CuratorTransactionFinal.class);
  TransactionCreateBuilder transactionCreateBuilder=mock(TransactionCreateBuilder.class);
  TransactionDeleteBuilder transactionDeleteBuilder=mock(TransactionDeleteBuilder.class);
  CuratorTransactionBridge curatorTransactionBridge=mock(CuratorTransactionBridge.class);
  when(curatorTransactionFinal.create()).thenReturn(transactionCreateBuilder);
  when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).build());
  when(transactionCreateBuilder.forPath("/test_job/sharding/0/instance","host0@-@0".getBytes())).thenReturn(curatorTransactionBridge);
  when(transactionCreateBuilder.forPath("/test_job/sharding/1/instance","host0@-@0".getBytes())).thenReturn(curatorTransactionBridge);
  when(transactionCreateBuilder.forPath("/test_job/sharding/2/instance","host0@-@0".getBytes())).thenReturn(curatorTransactionBridge);
  when(curatorTransactionBridge.and()).thenReturn(curatorTransactionFinal);
  when(curatorTransactionFinal.delete()).thenReturn(transactionDeleteBuilder);
  when(transactionDeleteBuilder.forPath("/test_job/leader/sharding/necessary")).thenReturn(curatorTransactionBridge);
  when(curatorTransactionBridge.and()).thenReturn(curatorTransactionFinal);
  when(curatorTransactionFinal.delete()).thenReturn(transactionDeleteBuilder);
  when(transactionDeleteBuilder.forPath("/test_job/leader/sharding/processing")).thenReturn(curatorTransactionBridge);
  when(curatorTransactionBridge.and()).thenReturn(curatorTransactionFinal);
  Map<JobInstance,List<Integer>> shardingResult=new HashMap<>();
  shardingResult.put(new JobInstance("host0@-@0"),Arrays.asList(0,1,2));
  ShardingService.PersistShardingInfoTransactionExecutionCallback actual=shardingService.new PersistShardingInfoTransactionExecutionCallback(shardingResult);
  actual.execute(curatorTransactionFinal);
  verify(curatorTransactionFinal,times(3)).create();
  verify(curatorTransactionFinal,times(2)).delete();
  verify(transactionDeleteBuilder).forPath("/test_job/leader/sharding/necessary");
  verify(transactionDeleteBuilder).forPath("/test_job/leader/sharding/processing");
  verify(curatorTransactionBridge,times(5)).and();
}
