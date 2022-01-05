public void testQueryByActivityId(){
  processEngineConfiguration.getCommandExecutor().execute(new Command<Void>(){
    public Void execute(    CommandContext commandContext){
      MessageEventSubscriptionEntity messageEventSubscriptionEntity1=commandContext.getEventSubscriptionEntityManager().createMessageEventSubscription();
      messageEventSubscriptionEntity1.setEventName("messageName");
      messageEventSubscriptionEntity1.setActivityId("someActivity");
      commandContext.getEventSubscriptionEntityManager().insert(messageEventSubscriptionEntity1);
      MessageEventSubscriptionEntity messageEventSubscriptionEntity2=commandContext.getEventSubscriptionEntityManager().createMessageEventSubscription();
      messageEventSubscriptionEntity2.setEventName("messageName");
      messageEventSubscriptionEntity2.setActivityId("someActivity");
      commandContext.getEventSubscriptionEntityManager().insert(messageEventSubscriptionEntity2);
      SignalEventSubscriptionEntity signalEventSubscriptionEntity3=commandContext.getEventSubscriptionEntityManager().createSignalEventSubscription();
      signalEventSubscriptionEntity3.setEventName("messageName2");
      signalEventSubscriptionEntity3.setActivityId("someOtherActivity");
      commandContext.getEventSubscriptionEntityManager().insert(signalEventSubscriptionEntity3);
      return null;
    }
  }
);
  List<EventSubscriptionEntity> list=newEventSubscriptionQuery().activityId("someOtherActivity").list();
  assertEquals(1,list.size());
  list=newEventSubscriptionQuery().activityId("someActivity").eventType("message").list();
  assertEquals(2,list.size());
  cleanDb();
}
