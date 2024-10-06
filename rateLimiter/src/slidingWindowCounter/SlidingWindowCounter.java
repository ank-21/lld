package slidingWindowCounter;

import java.time.Instant;

public class SlidingWindowCounter {
    private long windowSizeInSeconds;
    private long maxRequestsPerWindow;
    private long currentWindowStart;
    private long currentWindowCount;
    private long previousWindowCount;

    public SlidingWindowCounter(long windowSizeInSeconds, long maxRequestsPerWindow) {
        this.windowSizeInSeconds = windowSizeInSeconds;
        this.maxRequestsPerWindow = maxRequestsPerWindow;
        this.currentWindowStart = Instant.now().getEpochSecond();
        this.currentWindowCount = 0;
        this.previousWindowCount = 0;
    }

    public synchronized boolean allowRequest(){
        long now = Instant.now().getEpochSecond();
        long timePassedInWindow = now - currentWindowStart;
        if(timePassedInWindow >= windowSizeInSeconds){
            previousWindowCount = currentWindowCount;
            currentWindowCount = 0;
            currentWindowStart = now;
            timePassedInWindow = 0;
        }

        double weightedCount = (windowSizeInSeconds - timePassedInWindow) * previousWindowCount / (double) windowSizeInSeconds + currentWindowCount;
        if(weightedCount < maxRequestsPerWindow){
            currentWindowCount++;
            return true;
        }
        return false;
    }
}
