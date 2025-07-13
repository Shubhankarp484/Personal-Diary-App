package net.shubhankarpotnis.diaryApp.service;

import net.shubhankarpotnis.diaryApp.DiaryApplication;
import net.shubhankarpotnis.diaryApp.entity.DiaryEntry;
import net.shubhankarpotnis.diaryApp.repository.DiaryEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DiaryEntryService {

    @Autowired
    private DiaryEntryRepository diaryEntryRepository;

    public void saveEntry(DiaryEntry diaryEntry){
        diaryEntryRepository.save(diaryEntry);
    }

    public List<DiaryEntry> getAll(){
        return diaryEntryRepository.findAll();
    }

    public Optional<DiaryEntry> findById(ObjectId id){
        return diaryEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        diaryEntryRepository.deleteById(id);
    }
}

// controller ---> service ---> repository