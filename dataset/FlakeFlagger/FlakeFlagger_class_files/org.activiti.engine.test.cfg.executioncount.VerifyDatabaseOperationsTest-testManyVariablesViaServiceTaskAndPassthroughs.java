package org.activiti.engine.test.cfg.executioncount;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.cfg.CommandExecutorImpl;
import org.activiti.engine.impl.db.DbSqlSessionFactory;
import org.activiti.engine.impl.history.DefaultHistoryManager;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.profiler.ActivitiProfiler;
import org.activiti.engine.test.profiler.CommandStats;
import org.activiti.engine.test.profiler.ConsoleLogger;
import org.activiti.engine.test.profiler.ProfileSession;
import org.activiti.engine.test.profiler.ProfilingDbSqlSessionFactory;
import org.activiti.engine.test.profiler.TotalExecutionTimeCommandInterceptor;
import org.junit.Assert;

/**

 */
public class VerifyDatabaseOperationsTest extends PluggableActivitiTestCase {
  
  protected boolean oldExecutionTreeFetchValue;
  protected boolean oldExecutionRelationshipCountValue;
  protected boolean oldenableProcessDefinitionInfoCacheValue;
  protected CommandInterceptor oldFirstCommandInterceptor;
  protected DbSqlSessionFactory oldDbSqlSessionFactory;
  protected HistoryLevel oldHistoryLevel;
  
  public void testManyVariablesViaServiceTaskAndPassthroughs() {
	deployStartProcessInstanceAndProfile("process-variables-servicetask02.bpmn20.xml",
			"process-variables-servicetask02");
	assertDatabaseSelects("StartProcessInstanceCmd", "selectLatestProcessDefinitionByKey", 1L);
	assertDatabaseInserts("StartProcessInstanceCmd", "HistoricVariableInstanceEntityImpl-bulk-with-50", 1L,
			"HistoricProcessInstanceEntityImpl", 1L, "HistoricActivityInstanceEntityImpl-bulk-with-9", 1L);
	assertNoUpdatesAndDeletes("StartProcessInstanceCmd");
	Assert.assertEquals(0, runtimeService.createProcessInstanceQuery().count());
	Assert.assertEquals(1, historyService.createHistoricProcessInstanceQuery().finished().count());
}
  
  
  
  
  // ---------------------------------
  // HELPERS
  // ---------------------------------
  
  protected void assertExecutedCommands(String...commands) {
    ProfileSession profileSession = ActivitiProfiler.getInstance().getProfileSessions().get(0);
    Map<String, CommandStats> allStats = profileSession.calculateSummaryStatistics();
    
    if (commands.length != allStats.size()) {
      System.out.println("Following commands were found: ");
      for (String command : allStats.keySet()) {
        System.out.println(command);
      }
    }
    Assert.assertEquals(commands.length, allStats.size());
    
    for (String command : commands) {
      Assert.assertNotNull("Could not get stats for " + command, getStatsForCommand(command, allStats));
    }
  }

  protected void assertDatabaseSelects(String commandClass, Object ... expectedSelects) {
    CommandStats stats = getStats(commandClass);
    if (expectedSelects.length / 2 != stats.getDbSelects().size()) {
      Assert.fail("Unexpected number of database selects : " + stats.getDbSelects().size());
    }
    
    for (int i=0; i<expectedSelects.length; i+=2) {
      String dbSelect = (String) expectedSelects[i];
      Long count = (Long) expectedSelects[i+1];
      
      Assert.assertEquals("Wrong select count for " + dbSelect, count, stats.getDbSelects().get(dbSelect));
    }
  }

  protected void assertDatabaseInserts(String commandClass, Object ... expectedInserts) {
    CommandStats stats = getStats(commandClass);
    
    if (expectedInserts.length / 2 != stats.getDbInserts().size()) {
      Assert.fail("Unexpected number of database inserts : " + stats.getDbInserts().size() + ", but expected " + expectedInserts.length / 2);
    }
    
    for (int i=0; i<expectedInserts.length; i+=2) {
      String dbInsert = (String) expectedInserts[i];
      Long count = (Long) expectedInserts[i+1];
      
      Assert.assertEquals("Insert count for " + dbInsert + "not correct", count, stats.getDbInserts().get("org.activiti.engine.impl.persistence.entity." + dbInsert));
    }
  }
  
  protected void assertDatabaseDeletes(String commandClass,  Object ... expectedDeletes) {
    CommandStats stats = getStats(commandClass);
    
    if (expectedDeletes.length / 2 != stats.getDbDeletes().size()) {
      Assert.fail("Unexpected number of database deletes : " + stats.getDbDeletes().size());
    }
    
    for (int i=0; i<expectedDeletes.length; i+=2) {
      String dbDelete = (String) expectedDeletes[i];
      Long count = (Long) expectedDeletes[i+1];
      
      Assert.assertEquals("Delete count count for " + dbDelete + "not correct", count, stats.getDbDeletes().get("org.activiti.engine.impl.persistence.entity." + dbDelete));
    }
  }
  
  protected void assertNoInserts(String commandClass) {
    CommandStats stats = getStats(commandClass);
    Assert.assertEquals(0, stats.getDbInserts().size());
  }
  
  protected void assertNoUpdatesAndDeletes(String commandClass) {
    assertNoDeletes(commandClass);
    assertNoUpdates(commandClass);
  }
  
  protected void assertNoDeletes(String commandClass) {
    CommandStats stats = getStats(commandClass);
    Assert.assertEquals(0, stats.getDbDeletes().size());
  }
  
  protected void assertNoUpdates(String commandClass) {
    CommandStats stats = getStats(commandClass);
    Assert.assertEquals(0, stats.getDbUpdates().size());
  }
  
  protected CommandStats getStats(String commandClass) {
    ProfileSession profileSession = ActivitiProfiler.getInstance().getProfileSessions().get(0);
    Map<String, CommandStats> allStats = profileSession.calculateSummaryStatistics();
    CommandStats stats = getStatsForCommand(commandClass, allStats);
    return stats;
  }

  protected CommandStats getStatsForCommand(String commandClass, Map<String, CommandStats> allStats) {
    String clazz = commandClass;
    if (!clazz.startsWith("org.activiti")) {
      clazz = "org.activiti.engine.impl.cmd." + clazz;
    }
    CommandStats stats = allStats.get(clazz);
    return stats;
  }

  
  // HELPERS
  
  protected ActivitiProfiler deployStartProcessInstanceAndProfile(String path, String processDefinitionKey) {
    return deployStartProcessInstanceAndProfile(path, processDefinitionKey, true);
  }
  
  protected ActivitiProfiler deployStartProcessInstanceAndProfile(String path, String processDefinitionKey, boolean stopProfilingAfterStart) {
    deploy(path);
    ActivitiProfiler activitiProfiler = startProcessInstanceAndProfile(processDefinitionKey);
    if (stopProfilingAfterStart) {
      stopProfiling();
    }
    return activitiProfiler;
  }
  
  protected void deploy(String path) {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/cfg/executioncount/" + path).deploy();
  }
  
  protected ActivitiProfiler startProcessInstanceAndProfile(String processDefinitionKey) {
    ActivitiProfiler activitiProfiler = ActivitiProfiler.getInstance();
    activitiProfiler.startProfileSession("Profiling session");
    runtimeService.startProcessInstanceByKey(processDefinitionKey);
    return activitiProfiler;
  }
  
  protected void stopProfiling() {
    ActivitiProfiler profiler = ActivitiProfiler.getInstance();
    profiler.stopCurrentProfileSession();
    new ConsoleLogger(profiler).log();;
  }

}
