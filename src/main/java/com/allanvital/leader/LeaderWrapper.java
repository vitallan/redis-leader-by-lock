package com.allanvital.leader;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

@Service
public class LeaderWrapper {

	private static final int LOCK_TIMEOUT = 5000;
	private static final String LEADER_LOCK = "LEADER_LOCK";
	private Jedis jedis;
	private String myApplicationId;

	public LeaderWrapper(Jedis jedis, String myApplicationId) {
		this.jedis = jedis;
		this.myApplicationId = myApplicationId;
	}

	@Scheduled(fixedDelay = 2000)
	public void tryToAcquireLock() {
		jedis.set(LEADER_LOCK, "" + myApplicationId, "NX", "PX", LOCK_TIMEOUT);
		if (this.amILeader()) {
			System.out.println("["+ myApplicationId +"] Who's the boss now?!");
		} else {
			System.out.println("["+ myApplicationId +"] It's sad being a non-leader node :-( ");
		}
	}
	
	@Scheduled(fixedDelay = 5000)
	public void batchInLeader() {
		this.tryToAcquireLock(); // in case there is a hiatus in leadership, i'll take it by force
		if(this.amILeader()) {
			System.out.println("[" + myApplicationId + "] This should be a big batch job, wich will run only in leader node");
		} 
	}
	
	public boolean amILeader() {
		return myApplicationId.equals(jedis.get(LEADER_LOCK));
	}
	
}
