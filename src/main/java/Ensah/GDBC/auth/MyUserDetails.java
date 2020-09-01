package Ensah.GDBC.auth;

import Ensah.GDBC.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetails implements UserDetails {



    private long id ;
    private String username ;
    private String password ;




    public MyUserDetails(User user ) {
        super();
        this.id = user.getId() ;
        this.password = user.getPassword() ;
        this.username= user.getUsername();
    }






    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return null; }
    @Override
    public String getPassword() { return this.password; }
    @Override
    public String getUsername() {return this.username; }

    public long getId() {return this.id; }


    // this attribute user to configure the user account 

    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}
