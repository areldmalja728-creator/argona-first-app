package com.argona;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationManager {
    private static final Map<String, String> users = new HashMap<>();
    private static final Map<String, String> adminRoles = new HashMap<>();

    static {
        // Initialize demo users
        users.put("areldmalja728", "admin123"); // Admin user
        users.put("admin", "password123");      // Demo admin
        users.put("user1", "pass123");          // Regular user
        
        // Assign admin roles
        adminRoles.put("areldmalja728", "ADMIN");
        adminRoles.put("admin", "ADMIN");
        adminRoles.put("user1", "USER");
    }

    /**
     * Authenticate user credentials
     * @param username User's username
     * @param password User's password
     * @return true if credentials are valid, false otherwise
     */
    public static boolean authenticate(String username, String password) {
        if (!users.containsKey(username)) {
            return false;
        }
        return users.get(username).equals(password);
    }

    /**
     * Check if user is admin
     * @param username User's username
     * @return true if user has ADMIN role, false otherwise
     */
    public static boolean isAdmin(String username) {
        return "ADMIN".equals(adminRoles.get(username));
    }

    /**
     * Get user role
     * @param username User's username
     * @return User's role (ADMIN or USER)
     */
    public static String getUserRole(String username) {
        return adminRoles.getOrDefault(username, "USER");
    }

    /**
     * Verify admin access
     * @param username User's username
     * @param requiredRole Required role level
     * @return true if user has required permissions
     */
    public static boolean verifyAdminAccess(String username, String requiredRole) {
        String userRole = getUserRole(username);
        
        if ("ADMIN".equals(requiredRole)) {
            return isAdmin(username);
        }
        return true;
    }

    /**
     * Add new user (Admin only)
     * @param adminUsername Admin's username
     * @param newUsername New user's username
     * @param newPassword New user's password
     * @param role New user's role (ADMIN or USER)
     * @return true if user was added successfully
     */
    public static boolean addUser(String adminUsername, String newUsername, String newPassword, String role) {
        if (!isAdmin(adminUsername)) {
            System.out.println("Error: Only admins can add new users!");
            return false;
        }
        
        if (users.containsKey(newUsername)) {
            System.out.println("Error: Username already exists!");
            return false;
        }
        
        users.put(newUsername, newPassword);
        adminRoles.put(newUsername, role);
        System.out.println("User '" + newUsername + "' added successfully with role: " + role);
        return true;
    }

    /**
     * Delete user (Admin only)
     * @param adminUsername Admin's username
     * @param targetUsername Username to delete
     * @return true if user was deleted successfully
     */
    public static boolean deleteUser(String adminUsername, String targetUsername) {
        if (!isAdmin(adminUsername)) {
            System.out.println("Error: Only admins can delete users!");
            return false;
        }
        
        if (users.remove(targetUsername) != null) {
            adminRoles.remove(targetUsername);
            System.out.println("User '" + targetUsername + "' deleted successfully!");
            return true;
        }
        
        System.out.println("Error: User not found!");
        return false;
    }

    /**
     * Get all users (Admin only)
     * @param adminUsername Admin's username
     * @return Map of all users and their roles
     */
    public static Map<String, String> getAllUsers(String adminUsername) {
        if (!isAdmin(adminUsername)) {
            System.out.println("Error: Only admins can view all users!");
            return new HashMap<>();
        }
        
        Map<String, String> allUsers = new HashMap<>();
        for (String username : users.keySet()) {
            allUsers.put(username, adminRoles.get(username));
        }
        return allUsers;
    }
}
