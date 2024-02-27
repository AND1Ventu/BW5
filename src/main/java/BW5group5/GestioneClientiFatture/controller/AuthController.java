package BW5group5.GestioneClientiFatture.controller;

import BW5group5.GestioneClientiFatture.dto.UtenteRequest;
import BW5group5.GestioneClientiFatture.exception.BadRequestException;
import BW5group5.GestioneClientiFatture.exception.LoginFaultException;
import BW5group5.GestioneClientiFatture.dto.LoginRequest;
import BW5group5.GestioneClientiFatture.model.Utente;
import BW5group5.GestioneClientiFatture.security.JwtTools;
import BW5group5.GestioneClientiFatture.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private PasswordEncoder encoder;
    @PostMapping("/auth/register")
    public Utente register(@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return utenteService.saveUtente(utenteRequest);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        Utente utente = utenteService.getUtenteByUsername(loginRequest.getUsername());

        if(encoder.matches(loginRequest.getPassword(), utente.getPasswordHashed())){
            return jwtTools.createToken(utente);
        }
        else{
            throw new LoginFaultException("Username/Password errate");
        }

    }
}
