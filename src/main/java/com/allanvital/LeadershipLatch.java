package com.allanvital;

import com.allanvital.autoconfigure.LeadershipProperties;
import redis.clients.jedis.Jedis;

public class LeadershipLatch {

    private final LeadershipProperties leaderShipProperties;
    private String leaderLock;
    private int lockTimeout;
    private String myApplicationId;

    private Jedis jedis;

    public LeadershipLatch(Jedis jedis, LeadershipProperties leadershipProperties) {
        this.jedis = jedis;
        this.myApplicationId = leadershipProperties.getApplicationId();
        this.lockTimeout = leadershipProperties.getLockTimeout();
        this.leaderLock = leadershipProperties.getLockKey();
        this.leaderShipProperties = leadershipProperties;
        this.tryToAcquireLock();
    }

    private void tryToAcquireLock() {
        jedis.set(leaderLock, "" + myApplicationId, "NX", "PX", lockTimeout);
    }

    public boolean isLeaderNode() {
        tryToAcquireLock();
        return myApplicationId.equals(jedis.get(leaderLock));
    }

    public void close() {
        if(isLeaderNode()) {
            jedis.del(leaderLock);
        }
        jedis.set(leaderLock, "" + myApplicationId, "NX", "PX", 0);
    }

}
