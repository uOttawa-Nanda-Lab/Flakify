public void testQueryByEventSubscriptionId(){
  processEngineConfiguration.getCommandExecutor().execute(new Command<Void>(){
    public Void execute(    CommandContext commandContext){
      MessageEventSubscriptionEntity messageEventSubscriptionEntity1=commandContext.getEventSubscriptionEntityManager().createMessageEventSubscription();
      messageEventSubscriptionEntity1.setEventName("messageName");
      messageEventSubscriptionEntity1.setActivityId("someActivity");
      commandContext.getEventSubscriptionEntityManager().insert(messageEventSubscriptionEntity1);
      MessageEventSubscriptionEntity messageEventSubscriptionEntity2=commandContext.getEventSubscriptionEntityManager().createMessageEventSubscription();
      messageEventSubscriptionEntity2.setEventName("messageName");
      messageEventSubscriptionEntity2.setActivityId("someOtherActivity");
      commandContext.getEventSubscriptionEntityManager().insert(messageEventSubscriptionEntity2);
      return null;
    }
  }
);
  List<EventSubscriptionEntity> list=newEventSubscriptionQuery().activityId("someOtherActivity").list();
  assertEquals(1,list.size());
  final EventSubscriptionEntity entity=list.get(0);
  list=newEventSubscriptionQuery().eventSubscriptionId(entity.getId()).list();
  assertEquals(1,list.size());
  cleanDb();
}
