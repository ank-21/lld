package slidingWindowLog;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowLog {
    private long windowSizeInSeconds;
    private long maxRequestsPerWindow;
    private Queue<Long> requestLog;
    // Here we generate window start

    public SlidingWindowLog(long windowSizeInSeconds, long maxRequestsPerWindow) {
        this.windowSizeInSeconds = windowSizeInSeconds;
        this.maxRequestsPerWindow = maxRequestsPerWindow;
        this.requestLog = new LinkedList<>();
    }

    public boolean allowRequest(){
        long now = Instant.now().getEpochSecond();
        long windowStart = now - windowSizeInSeconds;  // Flexible window start

        while(!requestLog.isEmpty() && requestLog.peek() <= windowStart){
            requestLog.poll();
        }
        if(requestLog.size() < maxRequestsPerWindow){
            requestLog.offer(now);
            return true;
        }
        return false;
    }
}
