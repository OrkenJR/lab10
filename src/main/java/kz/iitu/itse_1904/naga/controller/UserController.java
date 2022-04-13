package kz.iitu.itse_1904.naga.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.iitu.itse_1904.naga.database.User;
import kz.iitu.itse_1904.naga.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

@Api
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService userService) {
        this.service = userService;
    }

    @GetMapping
    @ApiOperation(value = "getGreeting", notes="get greeting",nickname = "getGreeting")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        return new ResponseEntity<>(service.findAll( pageable), HttpStatus.OK);
    }

    @GetMapping("/sorted")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<User>> getAllUsersSorted(Pageable pageable) {
        return new ResponseEntity<>(service.findAllOrderById(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        User user = service.findById(id);
        HttpStatus status = HttpStatus.OK;
        if(user==null) status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(user, status);
    }

    @GetMapping("findByNames")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<User>> getUsersByName(@RequestBody String firstName, @RequestBody String lastName, Pageable pageable) {
        return new ResponseEntity<>(service.findAllByName(firstName, lastName, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<>(service.save(user), HttpStatus.OK);
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        service.delete(service.findById(id));
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<User> getUserByUsernameAndPassword(@RequestBody String username, @RequestBody String password){
        return new ResponseEntity<>(service.findByUsernameAndPassword(username, password), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return new ResponseEntity<>(service.save(user), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileToGetMetadata(@RequestParam MultipartFile multipartFile) throws IOException {

        String enter = "\n";
        File file = new File("src/main/resources/test.txt");

        BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);


        String res = "creationTime: " + attr.creationTime()+enter+
                "lastAccessTime: " + attr.lastAccessTime()+enter+
                "lastModifiedTime: " + attr.lastModifiedTime()+enter+
                "isDirectory: " + attr.isDirectory()+enter+
                "isOther: " + attr.isOther()+enter+
                "size: " + attr.size()+enter;

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> getUsersListAsTxt(Pageable pageable) throws IOException {

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(out, service.findAll(pageable));

        final byte[] data = out.toByteArray();

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
