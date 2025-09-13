package net.shubhankarpotnis.diaryApp.controller;


import net.shubhankarpotnis.diaryApp.apiResponse.QuoteApiResponse;
import net.shubhankarpotnis.diaryApp.entity.User;
import net.shubhankarpotnis.diaryApp.repository.UserRepository;
import net.shubhankarpotnis.diaryApp.service.QuotesService;
import net.shubhankarpotnis.diaryApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuotesService quotesService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
   //     if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
  //      }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/greeting")
    public String greeting(Authentication authentication) {
        String username = authentication.getName();

        // Fetch random quote
        QuoteApiResponse quoteApiResponse = quotesService.getQuote();

        // Fallback if author is empty
        String author = (quoteApiResponse.getQuoteAuthor() == null || quoteApiResponse.getQuoteAuthor().isEmpty()) ? "Unknown" : quoteApiResponse.getQuoteAuthor();

        return "Hi " + username + "\n\n" + "\"" + quoteApiResponse.getQuoteText() + "\" — " + author;
    }
}