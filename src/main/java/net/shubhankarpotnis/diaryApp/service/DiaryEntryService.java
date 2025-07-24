package net.shubhankarpotnis.diaryApp.service;

import lombok.extern.slf4j.Slf4j;
import net.shubhankarpotnis.diaryApp.entity.DiaryEntry;
import net.shubhankarpotnis.diaryApp.entity.User;
import net.shubhankarpotnis.diaryApp.repository.DiaryEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DiaryEntryService {

    @Autowired
    private DiaryEntryRepository diaryEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(DiaryEntry diaryEntry, String userName){
          try {
              User user = userService.findByUserName(userName);
              diaryEntry.setDate(LocalDateTime.now());
              DiaryEntry saved = diaryEntryRepository.save(diaryEntry);
              user.getDiaryEntries().add(saved);
              userService.saveEntry(user);
          } catch (Exception e){
              log.error("Exception ", e);
          }
    }

    public void saveEntry(DiaryEntry diaryEntry){
        diaryEntryRepository.save(diaryEntry);
    }

    public List<DiaryEntry> getAll(){
        return diaryEntryRepository.findAll();
    }

    public Optional<DiaryEntry> findById(ObjectId id){
        return diaryEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getDiaryEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        diaryEntryRepository.deleteById(id);
    }
}

// controller ---> service ---> repository