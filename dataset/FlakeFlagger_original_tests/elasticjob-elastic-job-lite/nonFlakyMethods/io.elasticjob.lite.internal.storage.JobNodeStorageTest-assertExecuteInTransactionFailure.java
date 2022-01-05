@Test(expected=RuntimeException.class) public void assertExecuteInTransactionFailure() throws Exception {
  CuratorFramework client=mock(CuratorFramework.class);
  CuratorTransaction curatorTransaction=mock(CuratorTransaction.class);
  TransactionCheckBuilder transactionCheckBuilder=mock(TransactionCheckBuilder.class);
  CuratorTransactionBridge curatorTransactionBridge=mock(CuratorTransactionBridge.class);
  CuratorTransactionFinal curatorTransactionFinal=mock(CuratorTransactionFinal.class);
  when(regCenter.getRawClient()).thenReturn(client);
  when(client.inTransaction()).thenReturn(curatorTransaction);
  when(curatorTransaction.check()).thenReturn(transactionCheckBuilder);
  when(transactionCheckBuilder.forPath("/")).thenReturn(curatorTransactionBridge);
  when(curatorTransactionBridge.and()).thenReturn(curatorTransactionFinal);
  TransactionCreateBuilder transactionCreateBuilder=mock(TransactionCreateBuilder.class);
  when(curatorTransactionFinal.create()).thenReturn(transactionCreateBuilder);
  when(transactionCreateBuilder.forPath("/test_transaction")).thenReturn(curatorTransactionBridge);
  when(curatorTransactionBridge.and()).thenThrow(new RuntimeException());
  jobNodeStorage.executeInTransaction(new TransactionExecutionCallback(){
    @Override public void execute(    final CuratorTransactionFinal curatorTransactionFinal) throws Exception {
      curatorTransactionFinal.create().forPath("/test_transaction").and();
    }
  }
);
  verify(regCenter).getRawClient();
  verify(client).inTransaction();
  verify(curatorTransaction).check();
  verify(transactionCheckBuilder).forPath("/");
  verify(curatorTransactionBridge,times(2)).and();
  verify(curatorTransactionFinal).create();
  verify(transactionCreateBuilder).forPath("/test_transaction");
  verify(curatorTransactionFinal,times(0)).commit();
}
