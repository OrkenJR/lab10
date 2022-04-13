package kz.iitu.itse_1904.naga.controller;

import kz.iitu.itse_1904.naga.database.Account;
import kz.iitu.itse_1904.naga.database.User;
import kz.iitu.itse_1904.naga.service.AccountService;
import kz.iitu.itse_1904.naga.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<Account>> getAllAccounts(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {

        Account account = service.findById(id);
        HttpStatus status = HttpStatus.OK;
        if(account==null) status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(account, status);
    }

    @GetMapping("/findByUser/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<Account>> getAccountsByUserId(@PathVariable Long id, Pageable pageable) {
        return new ResponseEntity<>(service.findAllByUserId(id, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> saveAccount(@RequestBody Account account){
        return new ResponseEntity<>(service.save(account), HttpStatus.OK);
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        service.delete(service.findById(id));
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<Account> updateAccount(@RequestBody Account account){
        return new ResponseEntity<>(service.save(account), HttpStatus.OK);
    }
}
