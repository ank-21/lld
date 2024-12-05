package main.java.com.phonepeTest.strategies;

import main.java.com.phonepeTest.models.CustomerServiceAgent;
import main.java.com.phonepeTest.models.Issue;
import main.java.com.phonepeTest.utils.exception.AgentNotFoundException;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinAssignment implements AssignmentStrategy {
    private final AtomicInteger currentIndex = new AtomicInteger(0);

    @Override
    public synchronized CustomerServiceAgent assignAgent(List<CustomerServiceAgent> agents, Issue issue) throws AgentNotFoundException {
        for (int i = 0; i < agents.size(); i++) {
            CustomerServiceAgent agent = agents.get((currentIndex.get() + i) % agents.size());
            if (agent.canHandleIssue(issue.getType())) {
                currentIndex.incrementAndGet();
                return agent;
            }
        }
        throw new AgentNotFoundException("No agent found for this issue");
    }
}
