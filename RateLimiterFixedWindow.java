/**
 * Fixed Window Rate Limiter (per user).
 *
 * Description:
 * This exercise implements a per-user rate limiter using the fixed window algorithm.
 * The goal is to limit how many requests a user can make within discrete
 * time windows of a given size (in seconds).
 *
 * How it works:
 * - Time is divided into fixed windows (cycles) based on windowSeconds.
 * - Each user tracks:
 *   - The current window (cycle)
 *   - The number of requests made in that window
 * - When a new request arrives:
 *   1) The current time window (cycle) is computed.
 *   2) If the user moved to a new window, the counter resets.
 *   3) If the request count is below the limit, it is allowed.
 *
 * Key properties:
 * - Fixed (non-sliding) time windows.
 * - Independent limiting per user.
 * - O(1) time per request.
 * - Minimal memory usage per user.
 *
 * Limitations:
 * - Boundary bursts are possible near window edges
 *   (e.g., many requests at the end of one window and
 *   again at the start of the next).
 * - Less precise than sliding window algorithms.
 *
 * Concepts practiced:
 * - HashMap-based per-user tracking
 * - Window-based rate limiting strategies
 * - Trade-offs between simplicity and accuracy
 * - Interview-style backend problem solving
 *
 * Possible extensions:
 * - Thread safety (ConcurrentHashMap)
 * - Distributed version using shared storage (Redis)
 * - Migration to sliding window for smoother limiting
 *
 * This is a classic introductory rate-limiting approach
 * often used to discuss trade-offs in system design interviews.
 */

package exercise;

import java.util.HashMap;

public class RateLimiterFixedWindow {
    public int maxRequests;
    public int windowSeconds;

    public int currentWindowRequest = 0;
    public long lastTime;

    private HashMap<String, UserData> stats = new HashMap<>();

    public RateLimiterFixedWindow(int maxRequests, int windowSeconds) {
        this.maxRequests = maxRequests;
        this.windowSeconds = windowSeconds;
    }
    public boolean allowRequest(String userId, long timestampSeconds) {
        UserData user = stats.get(userId);
        if (user == null) {
            user = new UserData();
            stats.put(userId, user);
        }

        int currentCycle = (int)timestampSeconds / windowSeconds;

        if (user.getCycle() != currentCycle) {
            user.setCycle(currentCycle);
            user.setRequests(0);
        }

        if (user.getRequests() < maxRequests) {
            user.addRequest();
            return true;
        }

        return false;
    }

    public class UserData {
        private int requests = 0;
        private int cycle = -1;

        public int getRequests() {
            return requests;
        }

        public void setRequests(int requests) {
            this.requests = requests;
        }

        public void addRequest() {
            this.requests += 1;
        }

        public int getCycle() {
            return cycle;
        }

        public void setCycle(int cycle) {
            this.cycle = cycle;
        }
    }

    public static void main(String[] args) {
        RateLimiterFixedWindow rl = new RateLimiterFixedWindow(3, 10);
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
