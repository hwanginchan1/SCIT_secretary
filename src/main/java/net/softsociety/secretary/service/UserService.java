package net.softsociety.secretary.service;

import net.softsociety.secretary.domain.User;

public interface UserService {
	void register(User user, String siteURL);
	
	boolean verify(String token);

	User findByEmailOrUserId(String emailOrUserId);

	boolean existsByEmail(String userEmail);

	boolean existsByUserId(String userId);
	
    void sendPasswordResetToken(String userEmail, String siteURL);
    
    User findByVerificationToken(String token);
    
    void resetPassword(User user, String newPassword);
}