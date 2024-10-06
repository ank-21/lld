package leakingBucket;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

public class LeakingBucket {
    private long capacity;
    private double leakRate;
    private Queue<Instant> bucket;
    private Instant lastLeakTimestamp;

    public LeakingBucket(long capacity, double leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;
        this.bucket = new LinkedList<>();
        this.lastLeakTimestamp = Instant.now();
    }

    public synchronized boolean allowRequest(){
        leak();     // First, leak out any requests based on elapsed time
        if(bucket.size() < capacity){
            bucket.offer(Instant.now());
            return true;
        }
        return false;   // Bucket is full
    }

    private void leak(){
        Instant now = Instant.now();
        long elapsedTime = now.toEpochMilli() - lastLeakTimestamp.toEpochMilli();
        long leakedItemsCount = (int) (elapsedTime / 1000 * leakRate);

        for(int i = 0; i < leakedItemsCount && !bucket.isEmpty(); i++){
            bucket.poll();
        }
        lastLeakTimestamp = now;
    }
}
