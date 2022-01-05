@Deployment public void testUuidGeneratorUsage(){
  ExecutorService executorService=Executors.newFixedThreadPool(10);
  for (int i=0; i < 50; i++) {
    executorService.execute(new Runnable(){
      public void run(){
        try {
          runtimeService.startProcessInstanceByKey("simpleProcess");
        }
 catch (        Exception e) {
          e.printStackTrace();
          fail();
        }
      }
    }
);
  }
  executorService.execute(new Runnable(){
    public void run(){
      boolean tasksFound=true;
      while (tasksFound) {
        List<Task> tasks=taskService.createTaskQuery().list();
        for (        Task task : tasks) {
          taskService.complete(task.getId());
        }
        tasksFound=taskService.createTaskQuery().count() > 0;
        if (!tasksFound) {
          try {
            Thread.sleep(1500L);
          }
 catch (          InterruptedException e) {
            e.printStackTrace();
          }
          tasksFound=taskService.createTaskQuery().count() > 0;
        }
      }
    }
  }
);
  try {
    executorService.shutdown();
    executorService.awaitTermination(1,TimeUnit.MINUTES);
  }
 catch (  InterruptedException e) {
    e.printStackTrace();
    fail();
  }
  assertEquals(50,historyService.createHistoricProcessInstanceQuery().count());
}
