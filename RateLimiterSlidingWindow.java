/**
 * Sliding Window Rate Limiter (per user).
 *
 * Description:
 * This exercise implements a per-user rate limiter using the sliding window algorithm.
 * The goal is to restrict how many requests a user can perform within a moving
 * time window (in seconds).
 *
 * How it works:
 * - Each user has an associated queue (Deque) storing timestamps of their recent requests.
 * - When a new request arrives:
 *   1) Old timestamps outside the time window are removed.
 *   2) The current number of valid requests is checked.
 *   3) If the limit is not exceeded, the request is allowed and the timestamp is recorded.
 *
 * Key properties:
 * - True sliding window (not fixed window).
 * - Independent limiting per user.
 * - O(1) amortized time per request.
 * - Memory usage proportional to maxRequests per active user.
 *
 * Concepts practiced:
 * - Data structures (HashMap + Deque)
 * - Rate limiting algorithms
 * - Time-window management
 * - Backend interview-style problem solving
 * - Foundations for scalable API protection
 *
 * Possible extensions:
 * - Thread safety (ConcurrentHashMap, per-user locks)
 * - Cleanup of inactive users
 * - Distributed version using Redis
 * - Alternative algorithms (Token Bucket / Leaky Bucket)
 *
 * This is a common backend interview exercise to evaluate
 * data structure knowledge, complexity analysis, and system design thinking.
 */

package exercise;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class RateLimiterSlidingWindow {
    public int maxRequests;
    public int windowSeconds;

    private HashMap<String, Deque<Long>> map = new HashMap<>();

    public RateLimiterSlidingWindow(int maxRequests, int windowSeconds) {
        this.maxRequests = maxRequests;
        this.windowSeconds = windowSeconds;
    }
    public boolean allowRequest(String userId, long timestampSeconds) {
        map.putIfAbsent(userId, new ArrayDeque<>());
        Deque<Long> q = map.get(userId);

        long limit = timestampSeconds - windowSeconds;

        while (!q.isEmpty() && q.peekFirst() <= limit) {
            q.pollFirst();
        }

        if (q.size() >= maxRequests) {
            return false;
        }

        q.addLast(timestampSeconds);

        return true;
    }

    public static void main(String[] args) {
        RateLimiterSlidingWindow rl = new RateLimiterSlidingWindow(3, 10);
        String userId = "u1";
        String userId2 = "u2";
        System.out.println(rl.allowRequest(userId, 1)); //true
        System.out.println(rl.allowRequest(userId2, 12)); //true
        System.out.println(rl.allowRequest(userId2, 14)); //true
        System.out.println(rl.allowRequest(userId, 2)); //true
        System.out.println(rl.allowRequest(userId, 3)); //true
        System.out.println(rl.allowRequest(userId, 5)); //false
        System.out.println(rl.allowRequest(userId2, 15)); //true
        System.out.println(rl.allowRequest(userId2, 16)); //false
        System.out.println(rl.allowRequest(userId, 6)); //false
        System.out.println(rl.allowRequest(userId, 12)); //true
    }
}
