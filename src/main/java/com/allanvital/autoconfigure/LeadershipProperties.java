package com.allanvital.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;
import java.util.UUID;

@ConfigurationProperties(prefix = "com.allanvital.leadership")
public class LeadershipProperties {

    private int lockTimeout;
    private String applicationId;
    private String lockKey;

    public int getLockTimeout() {
        if (lockTimeout == 0) {
            this.setLockTimeout(5000);
        }
        return lockTimeout;
    }

    public void setLockTimeout(int lockTimeout) {
        this.lockTimeout = lockTimeout;
    }

    public String getApplicationId() {
        if (applicationId == null) {
            this.setApplicationId(UUID.randomUUID().toString());
        }
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getLockKey() {
        if (lockKey == null) {
            this.setLockKey("LEADER_LOCK");
        }
        return lockKey;
    }

    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }

    @Override
    public String toString() {
        return "LeadershipProperties{" +
                "lockTimeout=" + lockTimeout +
                ", applicationId='" + applicationId + '\'' +
                ", lockKey='" + lockKey + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeadershipProperties that = (LeadershipProperties) o;
        return lockTimeout == that.lockTimeout &&
                Objects.equals(applicationId, that.applicationId) &&
                Objects.equals(lockKey, that.lockKey);
    }

    @Override
    public int hashCode() {

        return Objects.hash(lockTimeout, applicationId, lockKey);
    }
}
