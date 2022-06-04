package it.uniroma3.siw.catering.oauth;

public interface RoleService {
	void addRole(String uid, String role) throws Exception;

	void removeRole(String uid, String role);

}