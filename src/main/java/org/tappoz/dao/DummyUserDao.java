package org.tappoz.dao;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.tappoz.bean.AvatarPlayer;
import org.tappoz.bean.UserAddress;
import org.tappoz.bean.UserName;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

@Repository
public class DummyUserDao {
	
	public enum UserNamesEnum {
		mario,
		luigi,
		princessPeach,
		wario;
		
		public static UserNamesEnum getRandomUserNamesEnum() {
			Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
	}

    private final static String RANDOM_STR_PREFIX = "just another user, random value: ";
	private static BiMap<String, AvatarPlayer> USERS = HashBiMap.create();
	static {
		// http://en.wikipedia.org/wiki/List_of_Mario_franchise_characters
		UserName marioUn = UserName.getNewInstance(UserNamesEnum.mario.name());
		UserAddress marioUa = new UserAddress("Mario City", "NES", "Digital Country", "Mario's Road");
		AvatarPlayer marioUser = new AvatarPlayer(marioUn, marioUa, RANDOM_STR_PREFIX + StringUtils.quote(RandomStringUtils.random(5)));
		USERS.put(marioUser.getUserName().getUserName(), marioUser);
		UserName luigiUn = UserName.getNewInstance(UserNamesEnum.luigi.name());
		UserAddress luigiUa = new UserAddress("Luigi City", "NES", "Digital Country", "Luigi's Road");
		AvatarPlayer luigiUser = new AvatarPlayer(luigiUn, luigiUa, RANDOM_STR_PREFIX + StringUtils.quote(RandomStringUtils.random(5)));
		USERS.put(luigiUser.getUserName().getUserName(), luigiUser);
		UserName princessPeachUn = UserName.getNewInstance(UserNamesEnum.princessPeach.name());
		UserAddress princessPeachUa = new UserAddress("Princess Peach City", "NES", "Digital Country", "Princess Peach's Road");
		AvatarPlayer princessPeachUser = new AvatarPlayer(princessPeachUn, princessPeachUa, RANDOM_STR_PREFIX + StringUtils.quote(RandomStringUtils.random(5)));
		USERS.put(princessPeachUser.getUserName().getUserName(), princessPeachUser);
		UserName warioUn = UserName.getNewInstance(UserNamesEnum.wario.name());
		UserAddress warioUa = new UserAddress("Wario City", "NES", "Digital Country", "Wario's Road");
		AvatarPlayer warioUser = new AvatarPlayer(warioUn, warioUa, RANDOM_STR_PREFIX + StringUtils.quote(RandomStringUtils.random(5)));
		USERS.put(warioUser.getUserName().getUserName(), warioUser);
	}
	
	public boolean isUserAllowed(String userName) {
		
		return USERS.containsKey(userName);
	}
	
	public AvatarPlayer findUser(String userName) throws IllegalAccessException {
		
		AvatarPlayer foundUser = USERS.get(userName);
		
		if (foundUser != null)
			return foundUser;
		else
			throw new IllegalAccessException("This requested user can not be find: " + userName);
	}
	
	/**
	 * This method returns a random user saved in the BiMap (cfr. Google Guava BiMaps).
	 * 
	 * @return
	 * @throws IllegalAccessException
	 */
	public AvatarPlayer getRandomUser() throws IllegalAccessException {
		
		
		return findUser(UserNamesEnum.getRandomUserNamesEnum().name());
	}

}
