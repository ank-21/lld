package fixedWindow;

import java.time.Instant;

public class FixedWindow {
    private long windowSizeInSeconds;
    private long maxRequestsPerWindow;
    private long currentWindowStart;
    private long requestCount;

    public FixedWindow(long windowSizeInSeconds, long maxRequestsPerWindow) {
        this.windowSizeInSeconds = windowSizeInSeconds;
        this.maxRequestsPerWindow = maxRequestsPerWindow;
        this.currentWindowStart = Instant.now().getEpochSecond();
        this.requestCount = 0;
    }

    public synchronized boolean allowRequest(){
        long now = Instant.now().getEpochSecond();
        if(now - currentWindowStart > windowSizeInSeconds){
            requestCount = 0;
            currentWindowStart = now;
        }
        if(requestCount < maxRequestsPerWindow){
            requestCount++;
            return true;
        }
        return false;
    }
}
