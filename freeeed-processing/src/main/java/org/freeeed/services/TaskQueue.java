/**
 *
 * Copyright SHMsoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Facade for tasks. Behind is either SQS or local FIFO implementation.
 */
package org.freeeed.services;

/**
 *
 * @author mark
 */
public class TaskQueue {

    protected TaskQueue() {
        // Singleton
    }
    private final TaskQueue taskQueueService = init();

    private TaskQueue init() {
        if (Services.isLocal()) {
            return new LocalTaskQueue();
        } else if (Services.isAws()) {
            return new AwsTaskQueue();
        } else {
            return null;
        }
    }

    public Task getNext() {
        return taskQueueService.getNext();
    }

    public void add(Task task) {
        taskQueueService.add(task);
    }

    public void confirmDone(Task task) {
        taskQueueService.confirmDone(task);
    }

    public int size() {
        return taskQueueService.size();
    }
}
