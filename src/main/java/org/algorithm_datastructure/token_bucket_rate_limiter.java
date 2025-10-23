package org.algorithm_datastructure;

import java.util.HashMap;
import java.util.Map;

/**
 * Token Bucket Rate Limiter Implementation
 *
 * The token bucket algorithm is a rate limiting algorithm that controls the rate
 * of requests by maintaining a bucket of tokens. Each request consumes one token,
 * and tokens are refilled at a constant rate.
 *
 * Characteristics:
 * - Allows burst traffic up to bucket capacity
 * - Tokens are added at a fixed rate (refill rate)
 * - If bucket is full, new tokens are discarded
 * - If no tokens available, request is rejected
 *
 * Use cases:
 * - API rate limiting
 * - Network traffic shaping
 * - Request throttling
 *
 * Time Complexity: O(1) for allowRequest
 * Space Complexity: O(n) where n is number of users/endpoints
 */
public class token_bucket_rate_limiter {

    /**
     * TokenBucket class represents a single token bucket for rate limiting
     */
    static class TokenBucket {
        private final long capacity;           // Maximum number of tokens
        private final long refillRate;         // Tokens added per second
        private double availableTokens;        // Current available tokens
        private long lastRefillTimestamp;      // Last time tokens were refilled

        /**
         * Constructor for TokenBucket
         * @param capacity Maximum number of tokens in bucket
         * @param refillRate Number of tokens added per second
         */
        public TokenBucket(long capacity, long refillRate) {
            this.capacity = capacity;
            this.refillRate = refillRate;
            this.availableTokens = capacity;
            this.lastRefillTimestamp = System.currentTimeMillis();
        }

        /**
         * Attempts to consume a token for a request
         * @return true if request is allowed, false otherwise
         */
        public synchronized boolean allowRequest() {
            refill();

            if (availableTokens >= 1) {
                availableTokens -= 1;
                return true;
            }

            return false;
        }

        /**
         * Refills tokens based on time elapsed since last refill
         */
        private void refill() {
            long currentTime = System.currentTimeMillis();
            long timeSinceLastRefill = currentTime - lastRefillTimestamp;

            // Calculate tokens to add based on time elapsed
            double tokensToAdd = (timeSinceLastRefill / 1000.0) * refillRate;

            // Add tokens but don't exceed capacity
            availableTokens = Math.min(capacity, availableTokens + tokensToAdd);
            lastRefillTimestamp = currentTime;
        }

        /**
         * Gets current available tokens (for testing)
         * @return number of available tokens
         */
        public synchronized double getAvailableTokens() {
            refill();
            return availableTokens;
        }
    }

    /**
     * RateLimiter class manages multiple token buckets for different users/endpoints
     */
    static class RateLimiter {
        private final Map<String, TokenBucket> buckets;
        private final long capacity;
        private final long refillRate;

        /**
         * Constructor for RateLimiter
         * @param capacity Maximum tokens per bucket
         * @param refillRate Tokens added per second per bucket
         */
        public RateLimiter(long capacity, long refillRate) {
            this.buckets = new HashMap<>();
            this.capacity = capacity;
            this.refillRate = refillRate;
        }

        /**
         * Checks if request is allowed for given user/endpoint
         * @param key Unique identifier (user:endpoint)
         * @return true if request is allowed, false otherwise
         */
        public boolean allowRequest(String key) {
            TokenBucket bucket = buckets.computeIfAbsent(key,
                k -> new TokenBucket(capacity, refillRate));
            return bucket.allowRequest();
        }

        /**
         * Gets available tokens for a key (for testing)
         * @param key Unique identifier
         * @return available tokens
         */
        public double getAvailableTokens(String key) {
            TokenBucket bucket = buckets.get(key);
            return bucket != null ? bucket.getAvailableTokens() : capacity;
        }
    }

    public static void main(String[] args) {
        System.out.println("Token Bucket Rate Limiter Demo\n");

        // Test 1: Basic rate limiting
        System.out.println("Test 1: Basic Rate Limiting");
        System.out.println("Creating bucket with capacity=5, refillRate=2 tokens/sec");
        TokenBucket bucket = new TokenBucket(5, 2);

        // Make 5 requests (should all succeed)
        for (int i = 1; i <= 5; i++) {
            System.out.println("Request " + i + ": " +
                (bucket.allowRequest() ? "Allowed" : "Rejected"));
        }

        // Next request should be rejected (bucket empty)
        System.out.println("Request 6: " +
            (bucket.allowRequest() ? "Allowed" : "Rejected"));
        System.out.println();

        // Test 2: Token refill
        System.out.println("Test 2: Token Refill");
        System.out.println("Waiting 1 second for refill...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Available tokens: " + bucket.getAvailableTokens());
        System.out.println("Request after refill: " +
            (bucket.allowRequest() ? "Allowed" : "Rejected"));
        System.out.println();

        // Test 3: Multiple users/endpoints
        System.out.println("Test 3: Multiple Users/Endpoints");
        RateLimiter limiter = new RateLimiter(3, 1);

        String user1 = "user1:api/getData";
        String user2 = "user2:api/getData";

        // User 1 makes 3 requests
        System.out.println("User 1 requests:");
        for (int i = 1; i <= 4; i++) {
            System.out.println("  Request " + i + ": " +
                (limiter.allowRequest(user1) ? "Allowed" : "Rejected"));
        }

        // User 2 makes 3 requests (should have their own bucket)
        System.out.println("User 2 requests:");
        for (int i = 1; i <= 4; i++) {
            System.out.println("  Request " + i + ": " +
                (limiter.allowRequest(user2) ? "Allowed" : "Rejected"));
        }
        System.out.println();

        // Test 4: Burst traffic handling
        System.out.println("Test 4: Burst Traffic Handling");
        TokenBucket burstBucket = new TokenBucket(10, 5);

        System.out.println("Sending 10 rapid requests:");
        int allowed = 0, rejected = 0;
        for (int i = 0; i < 10; i++) {
            if (burstBucket.allowRequest()) {
                allowed++;
            } else {
                rejected++;
            }
        }
        System.out.println("Allowed: " + allowed + ", Rejected: " + rejected);

        System.out.println("\nWaiting 2 seconds for refill...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Available tokens after refill: " +
            burstBucket.getAvailableTokens());
        System.out.println("\nSending 10 more requests:");
        allowed = 0;
        rejected = 0;
        for (int i = 0; i < 10; i++) {
            if (burstBucket.allowRequest()) {
                allowed++;
            } else {
                rejected++;
            }
        }
        System.out.println("Allowed: " + allowed + ", Rejected: " + rejected);
    }
}
