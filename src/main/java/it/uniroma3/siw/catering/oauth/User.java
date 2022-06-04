package it.uniroma3.siw.catering.oauth;


import javax.persistence.*;

@Entity
@Table(name = "user_table")

public class User{// extends DefaultOAuth2User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String externalUid;
/*
	@Column(nullable = false)
	private String email;
 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExternalUid() {
		return externalUid;
	}

	public void setExternalUid(String externalUid) {
		this.externalUid = externalUid;
	}

	/*@ElementCollection(targetClass = MyPermission.class)
	@CollectionTable
	@Enumerated(EnumType.STRING)
	private Collection<MyPermission> permissions;
	 */
/*
	private String imageUrl;
	@Column(nullable = false)
	private UserType type;
	*/

	
}