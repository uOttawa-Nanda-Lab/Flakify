@Test public void test() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
  jtaPropertyManager.getJTAEnvironmentBean().setTransactionManagerClassName("com.arjuna.ats.internal.jta.transaction.arjunacore.TransactionManagerImple");
  final TransactionSynchronizationRegistry tsr=new TransactionSynchronizationRegistryWrapper();
  final JBossLocalTransactionProvider.Builder builder=JBossLocalTransactionProvider.builder();
  builder.setTransactionManager(com.arjuna.ats.jta.TransactionManager.transactionManager());
  builder.setExtendedJBossXATerminator(new XATerminatorImple());
  builder.setXAResourceRecoveryRegistry(new XAResourceRecoveryRegistry(){
    @Override public void addXAResourceRecovery(    XAResourceRecovery xaResourceRecovery){
    }
    @Override public void removeXAResourceRecovery(    XAResourceRecovery xaResourceRecovery){
    }
  }
);
  LocalTransactionContext.getContextManager().setGlobalDefault(new LocalTransactionContext(builder.build()));
  TransactionManager transactionManager=ContextTransactionManager.getInstance();
  transactionManager.begin();
  tsr.registerInterposedSynchronization(new Synchronization(){
    @Override public void beforeCompletion(){
      tsr.registerInterposedSynchronization(new Synchronization(){
        @Override public void beforeCompletion(){
          innerSyncCalled=true;
        }
        @Override public void afterCompletion(        int status){
        }
      }
);
    }
    @Override public void afterCompletion(    int status){
    }
  }
);
  transactionManager.commit();
  assertTrue(innerSyncCalled);
}
