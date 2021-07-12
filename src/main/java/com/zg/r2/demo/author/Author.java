package com.zg.r2.demo.author;

import com.zg.r2.demo.book.Book;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@ToString
public class Author implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @UniqeUserName
    @NotEmpty(message = "Kullanıcı adı olmalı")
    private String userName;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Parola bir küçük harf bir büyük harf ve bir karekter içermeli")
    private String password;

   
    private String role;
    
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "author",cascade = CascadeType.REMOVE)
    private List<Book> bookList;



    public Author(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public Author(long id,String userName, String password,String role) {
    	this.id=id;
    	this.userName = userName;
        this.password = password;
        this.role=role;
    }
    

    public Author() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_"+role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
