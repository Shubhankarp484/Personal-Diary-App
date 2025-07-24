package net.shubhankarpotnis.diaryApp.controller;


import net.shubhankarpotnis.diaryApp.entity.DiaryEntry;
import net.shubhankarpotnis.diaryApp.entity.User;
import net.shubhankarpotnis.diaryApp.repository.UserRepository;
import net.shubhankarpotnis.diaryApp.service.DiaryEntryService;
import net.shubhankarpotnis.diaryApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class DiaryEntryControllerV2 {

  @Autowired
  private DiaryEntryService diaryEntryService;

  @Autowired
  private UserService userService;
  

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllDairyEntriesOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<DiaryEntry> all = user.getDiaryEntries();
      if(all != null && !all.isEmpty()){
        return new ResponseEntity<>(all, HttpStatus.OK);
      }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<DiaryEntry> createEntry(@RequestBody DiaryEntry myEntry, @PathVariable String userName){
     try{
       diaryEntryService.saveEntry(myEntry, userName);
       return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
     }catch(Exception e){
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<DiaryEntry> getDiaryEntryById(@PathVariable ObjectId myId){
        Optional<DiaryEntry> diaryEntry = diaryEntryService.findById(myId);
        if (diaryEntry.isPresent()){
          return new ResponseEntity<>(diaryEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteDiaryEntryById(@PathVariable ObjectId myId, @PathVariable String userName){
         diaryEntryService.deleteById(myId, userName);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> updateDiaryById(
            @PathVariable ObjectId myId,
            @RequestBody DiaryEntry newEntry,
            @PathVariable String userName
    ){
      DiaryEntry oldEntry = diaryEntryService.findById(myId).orElse(null);
      if(oldEntry != null){
        oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : oldEntry.getTitle());
        oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : oldEntry.getContent());
        diaryEntryService.saveEntry(oldEntry);
        return new ResponseEntity<>(oldEntry, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}