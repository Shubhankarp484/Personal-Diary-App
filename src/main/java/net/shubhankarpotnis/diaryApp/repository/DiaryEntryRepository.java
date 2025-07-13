package net.shubhankarpotnis.diaryApp.repository;


import net.shubhankarpotnis.diaryApp.entity.DiaryEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiaryEntryRepository extends MongoRepository<DiaryEntry, ObjectId> {

}
