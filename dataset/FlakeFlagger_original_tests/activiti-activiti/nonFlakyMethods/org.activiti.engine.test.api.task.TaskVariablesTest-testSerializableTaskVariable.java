public void testSerializableTaskVariable(){
  Task task=taskService.newTask();
  task.setName("MyTask");
  taskService.saveTask(task);
  Map<String,Object> vars=new HashMap<String,Object>();
  MyVariable myVariable=new MyVariable("Hello world");
  vars.put("theVar",myVariable);
  taskService.setVariables(task.getId(),vars);
  MyVariable variable=(MyVariable)taskService.getVariable(task.getId(),"theVar");
  assertEquals("Hello world",variable.getValue());
  taskService.deleteTask(task.getId(),true);
}
