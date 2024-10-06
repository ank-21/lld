package tokenBucket;

import java.time.Instant;

public class TokenBucket {
    private static final long capacity = 2;        // Maximum number of tokens the bucket can hold
    private static final double fillRate = 4;      // Rate at which tokens are added to the bucket (tokens)
    private static final int fillRateTime = 3;     // Time after which the refill will be done (in seconds)
    private double currentTokens;        // Current number of tokens in the bucket
    private Instant lastRefillTimestamp; // Last time we refilled the bucket

    public TokenBucket() {
        this.currentTokens = capacity;  // Start with a full bucket
        this.lastRefillTimestamp = Instant.now();
    }

    public synchronized boolean allowRequest() {
        refill();  // First, add any new tokens based on elapsed time

        if (this.currentTokens == 0) {
            return false;  // Not enough tokens, deny the request
        }

        this.currentTokens -= 1;  // Consume the token
        return true;  // Allow the request
    }

    private void refill() {
        Instant now = Instant.now();

        // Check if the lastRefillTimeStamp is more than the refill rate time, refill it
        long timeElapsed = now.toEpochMilli() - lastRefillTimestamp.toEpochMilli();
        if (timeElapsed > fillRateTime * 1000) {
            this.currentTokens = capacity; // Reset to capacity if more than fillRateTime has passed
        } else {
            double tokensToAdd = (int)(timeElapsed / 1000.0) * (fillRate / fillRateTime);
            this.currentTokens = Math.min(capacity, this.currentTokens + tokensToAdd);
        }

        this.lastRefillTimestamp = now;
    }

    public double getCurrentTokens() {
        return currentTokens;
    }

    public Instant getLastRefillTimestamp() {
        return lastRefillTimestamp;
    }
}