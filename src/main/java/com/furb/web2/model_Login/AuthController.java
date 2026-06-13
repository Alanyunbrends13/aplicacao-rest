package com.furb.web2.model_Login;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furb.web2.model_Usuario.Usuario;
import com.furb.web2.model_Usuario.usuarioRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;
    private final usuarioRepository uRepository;
    private final PasswordEncoder passwordEncoder;

    //Construtor para instanciar o Serviço do JWT
    public AuthController(JwtService jwtService, usuarioRepository uRepository, PasswordEncoder passwordEncoder){
        this.jwtService = jwtService;
        this.uRepository = uRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println("ENTROU NO LOGIN");

        Optional<Usuario> usuarioBanco = uRepository.findByLogin(request.login());

        System.out.println("Usuário encontrado no banco: [" + usuarioBanco.get().getLogin() + "]");
        System.out.println("Senha no banco: [" + usuarioBanco.get().getSenha() + "]");
        System.out.println("Usuário enviado no request: [" + request.login() + "]");
        System.out.println("Senha enviada no request: [" + request.senha() + "]");

        
        if(usuarioBanco.isEmpty() || !passwordEncoder.matches(request.senha(), usuarioBanco.get().getSenha())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }

        /*if(!request.usuario().equals("usuario") || !request.senha().equals("admin")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }*/

        
        String token = jwtService.gerarToken(request.login());

        return ResponseEntity.ok(new LoginResponse(token));
        
    }

}
