package com.zg.r2.demo.author;


import javax.validation.constraints.Pattern;



import lombok.Data;
@Data
public class AuthorUpdate {
	
	private String name;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Bir küçük harf bir büyük harf ve bir karekter içermeli")
    private String password;

   
    private String role;



    
}
