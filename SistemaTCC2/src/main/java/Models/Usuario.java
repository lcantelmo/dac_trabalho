package Models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author MstfDryl
 */
@Entity(name="USERS")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries({
    @NamedQuery(name="allUsers", query="SELECT u FROM USERS u"),
    @NamedQuery(name="getUsername", query="SELECT u FROM USERS u WHERE u.userName = :username"),
    @NamedQuery(name="getEmail", query="SELECT u FROM USERS u WHERE u.email = :email")
})
public class Usuario implements Serializable {
    
    @Column(name="ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="USERNAME", length=200, nullable=false)
    private String userName;
    
    @Column(name="PASSWORD", length=255, nullable=false)
    private String password;
    
    @Column(name="EMAIL", length=255, nullable=false)
    private String email;
    	
    @Column(name="USER_TYPE", length=20, nullable=false)
    private String userType;
    
    @Column(name="MATRICULA", length=15, nullable=false)
    private String matricula;

    public Usuario() {
    	super();
    }
    
    public Usuario(Long id) {
    	super();
    	this.id = id;
    }
    
    public Usuario(String userName, String password, String email, String userType) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
    
}
