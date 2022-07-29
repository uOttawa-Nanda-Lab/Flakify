/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.engine.test.api.nonpublic;

import java.util.List;

import org.activiti.engine.impl.EventSubscriptionQueryImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.EventSubscriptionEntity;
import org.activiti.engine.impl.persistence.entity.EventSubscriptionEntityManager;
import org.activiti.engine.impl.persistence.entity.MessageEventSubscriptionEntity;
import org.activiti.engine.impl.persistence.entity.SignalEventSubscriptionEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;

/**

 */
public class EventSubscriptionQueryTest extends PluggableActivitiTestCase {

  public void testQueryByActivityId(){processEngineConfiguration.getCommandExecutor().execute(new Command<Void>(){public Void execute(CommandContext commandContext){MessageEventSubscriptionEntity messageEventSubscriptionEntity1=commandContext.getEventSubscriptionEntityManager().createMessageEventSubscription();messageEventSubscriptionEntity1.setEventName("messageName");messageEventSubscriptionEntity1.setActivityId("someActivity");commandContext.getEventSubscriptionEntityManager().insert(messageEventSubscriptionEntity1);MessageEventSubscriptionEntity messageEventSubscriptionEntity2=commandContext.getEventSubscriptionEntityManager().createMessageEventSubscription();messageEventSubscriptionEntity2.setEventName("messageName");messageEventSubscriptionEntity2.setActivityId("someActivity");commandContext.getEventSubscriptionEntityManager().insert(messageEventSubscriptionEntity2);SignalEventSubscriptionEntity signalEventSubscriptionEntity3=commandContext.getEventSubscriptionEntityManager().createSignalEventSubscription();signalEventSubscriptionEntity3.setEventName("messageName2");signalEventSubscriptionEntity3.setActivityId("someOtherActivity");commandContext.getEventSubscriptionEntityManager().insert(signalEventSubscriptionEntity3);return null;}});List<EventSubscriptionEntity> list=newEventSubscriptionQuery().activityId("someOtherActivity").list();assertEquals(1,list.size());list=newEventSubscriptionQuery().activityId("someActivity").eventType("message").list();assertEquals(2,list.size());cleanDb();}

  protected EventSubscriptionQueryImpl newEventSubscriptionQuery() {
    return new EventSubscriptionQueryImpl(processEngineConfiguration.getCommandExecutor());
  }

  protected void cleanDb() {
    processEngineConfiguration.getCommandExecutor().execute(new Command<Void>() {
      public Void execute(CommandContext commandContext) {
        final List<EventSubscriptionEntity> subscriptions = new EventSubscriptionQueryImpl(commandContext).list();
        for (EventSubscriptionEntity eventSubscriptionEntity : subscriptions) {
          EventSubscriptionEntityManager eventSubscriptionEntityManager = Context.getCommandContext().getEventSubscriptionEntityManager();
          eventSubscriptionEntityManager.delete(eventSubscriptionEntity);
        }
        return null;
      }
    });

  }

}
