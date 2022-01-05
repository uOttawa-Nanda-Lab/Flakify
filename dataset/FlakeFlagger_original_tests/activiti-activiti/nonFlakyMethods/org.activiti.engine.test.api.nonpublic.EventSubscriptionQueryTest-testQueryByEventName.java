public void testQueryByEventName(){
  processEngineConfiguration.getCommandExecutor().execute(new Command<Void>(){
    public Void execute(    CommandContext commandContext){
      MessageEventSubscriptionEntity messageEventSubscriptionEntity1=commandContext.getEventSubscriptionEntityManager().createMessageEventSubscription();
      messageEventSubscriptionEntity1.setEventName("messageName");
      commandContext.getEventSubscriptionEntityManager().insert(messageEventSubscriptionEntity1);
      MessageEventSubscriptionEntity messageEventSubscriptionEntity2=commandContext.getEventSubscriptionEntityManager().createMessageEventSubscription();
      messageEventSubscriptionEntity2.setEventName("messageName");
      commandContext.getEventSubscriptionEntityManager().insert(messageEventSubscriptionEntity2);
      MessageEventSubscriptionEntity messageEventSubscriptionEntity3=commandContext.getEventSubscriptionEntityManager().createMessageEventSubscription();
      messageEventSubscriptionEntity3.setEventName("messageName2");
      commandContext.getEventSubscriptionEntityManager().insert(messageEventSubscriptionEntity3);
      return null;
    }
  }
);
  List<EventSubscriptionEntity> list=newEventSubscriptionQuery().eventName("messageName").list();
  assertEquals(2,list.size());
  list=newEventSubscriptionQuery().eventName("messageName2").list();
  assertEquals(1,list.size());
  cleanDb();
}
